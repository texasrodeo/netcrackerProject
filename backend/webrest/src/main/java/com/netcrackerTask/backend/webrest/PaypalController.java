package com.netcrackerTask.backend.webrest;

import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Order;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.service.PaypalService;

import com.netcrackerTask.backend.business.service.StoreService;
import com.netcrackerTask.backend.business.service.UserService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class PaypalController {
    @Autowired
    PaypalService paypalService;
    @Autowired
    StoreService storeService;

    @Autowired
    UserService userService;

    private static final String url = "http://localhost:8080/";
    private static final String success = "pay/success";
    private static final String cancel = "pay/cancel";

    @GetMapping("/pay")
    public String getBuyPage(Model model, @RequestParam("id") Long id){
        List<Account> items = storeService.getBagItemsForUser(id);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null) {
            String name = auth.getName();
            List<Long> accountsId = new ArrayList<>();
            User user = userService.findByUsername(name);
            if(user.getActivationCode()!=null){
                model.addAttribute("autherror","Вы не подтвердили свой адрес электронной почты. " +
                        "Перейдите по ссылке в отправленном Вам письме для активации аккаунта.");
            }

        }
        model.addAttribute("items", items);
        model.addAttribute("id", id);
        model.addAttribute("sum", storeService.getPriceSum(items));
        return "checkout";
    }

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order, @RequestParam("userid") Long id)  {
        try {
            for (Account account: storeService.getBagItemsForUser(id)) {
                order.setAccountId(account.getId());
            }

            Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(),
                    url+cancel, paypalService.completeSuccessUrl(order.getAccountsId(),url+success));
            for(Links link:payment.getLinks()){
                if(link.getRel().equals("approval_url")){
                    return "redirect:"+link.getHref();
                }
            }
        }
        catch (PayPalRESTException e){
            e.printStackTrace();
        }
        return "redirect:/main";
    }

    @GetMapping(value = success)
    public String successPay(@RequestParam Map<String,String> allRequestParams){
        try {
            Payment payment = paypalService.executePayment(allRequestParams.get("paymentId"),allRequestParams.get("PayerID"));

            System.out.println(payment.toJSON());
            if(payment.getState().equals("approved")){
                Authentication auth= SecurityContextHolder.getContext().getAuthentication();
                if(auth!=null){
                    String name=auth.getName();
                    List<Long> accountsId = new ArrayList<>();
                    User user= userService.findByUsername(name);
                    allRequestParams.forEach((k,v)->{
                        if(k.contains("id")){
                            Long id = new Long(v);
                            accountsId.add(id);
                        }
                    });

                    storeService.sellAccounts(accountsId, user.getUsername(), user.getEmail());
                    return "success";
                }


            }

        }
        catch (PayPalRESTException e){
            System.out.println(e.getMessage());
        }
        return "redirect:/main";
    }

    @GetMapping(value = cancel)
    public String cancel(){
        return "cancel";
    }


}
