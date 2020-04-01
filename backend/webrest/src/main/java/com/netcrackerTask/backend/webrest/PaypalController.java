package com.netcrackerTask.backend.webrest;

import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Order;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.payloads.MessageResponse;
import com.netcrackerTask.backend.business.payloads.PayRequest;
import com.netcrackerTask.backend.business.service.PaypalService;

import com.netcrackerTask.backend.business.service.StoreService;
import com.netcrackerTask.backend.business.service.UserService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping("/checkout")
    public Map<String,Object> getBuyPage(){
        Map<String,Object> result = new HashMap<>();

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null) {
            String name = auth.getName();
            List<Long> accountsId = new ArrayList<>();
            User user = userService.findByUsername(name);
            if(user.getActivationCode()!=null){

                result.put("autherror","Вы не подтвердили свой адрес электронной почты. " +
                        "Перейдите по ссылке в отправленном Вам письме для активации аккаунта.");
            }
            List<Account> items = storeService.getBagItemsForUser(user.getId());
            result.put("items",items);
            result.put("sum",storeService.getPriceSum(items));


        }
        return result;
    }

//    @PostMapping("/pay")
//    public ResponseEntity<?> payment(@RequestBody PayRequest sum, HttpServletResponse response)    {
//        try {
//            Order order = new Order();
//            order.setMethod("paypal");
//            order.setIntent("sale");
//            order.setCurrency("RUB");
//
//            order.setPrice(sum.getSum());
//            order.setDescription("account purchase");
//            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//            String name=auth.getName();
//            User user= userService.findByUsername(name);
//            order.setAccountId(user.getId());
//            for (Account account: storeService.getBagItemsForUser(user.getId())) {
//                order.setAccountId(account.getId());
//            }
//
//            Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
//                    order.getIntent(), order.getDescription(),
//                    url+cancel, paypalService.completeSuccessUrl(order.getAccountsId(),url+success));
//
//            for(Links link:payment.getLinks()){
//                if(link.getRel().equals("approval_url")){
//                    response.sendRedirect(link.getHref());
//                }
//            }
//        }
//        catch (PayPalRESTException | IOException e){
//            e.printStackTrace();
//        }
//        return ResponseEntity
//                .badRequest()
//                .body(new MessageResponse("Ошибка"));
//    }



    @GetMapping("/pay")
    public Map<String,String> payment(@RequestParam String sum)    {
        Map<String,String> res = new HashMap<>();
        try {

            Order order = new Order();
            order.setMethod("paypal");
            order.setIntent("sale");
            order.setCurrency("RUB");

            order.setPrice(Long.parseLong(sum));
            order.setDescription("account purchase");
            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
            String name=auth.getName();
            User user= userService.findByUsername(name);
            order.setAccountId(user.getId());
            for (Account account: storeService.getBagItemsForUser(user.getId())) {
                order.setAccountId(account.getId());
            }

            Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(),
                    url+cancel, paypalService.completeSuccessUrl(order.getAccountsId(),url+success));

            for(Links link:payment.getLinks()){
                if(link.getRel().equals("approval_url")){
                    res.put("url",link.getHref()) ;
                    return res;
                }
            }
        }
        catch (PayPalRESTException e){
            e.printStackTrace();
        }
         return res;
    }

    @GetMapping("/pay/success")
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

    @GetMapping("/pay/cancel")
    public String cancel(){
        return "cancel";
    }


}
