package com.netcrackerTask.backend.webrest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Order;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.implementations.LogServiceImpl;
import com.netcrackerTask.backend.business.implementations.PaypalServiceImpl;
import com.netcrackerTask.backend.business.implementations.StoreServiceImpl;
import com.netcrackerTask.backend.business.implementations.UserServiceImpl;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class PaypalController {

    PaypalServiceImpl paypalService;

    StoreServiceImpl storeService;

    UserServiceImpl userService;

    LogServiceImpl logService;

    private static final String url = "http://localhost:4200/";

    private static final String success = "pay/success";

    private static final String cancel = "pay/cancel";

    /**
     * Constructor.
     * @param storeService service associated with store
     * @param paypalService service associated with PayPal
     * @param userService service associated with users
     * @param logService service associated with logging
     */
    @Autowired
    public PaypalController(final PaypalServiceImpl paypalService, final StoreServiceImpl storeService,
                            final UserServiceImpl userService, final LogServiceImpl logService){
        this.paypalService = paypalService;
        this.storeService = storeService;
        this.userService = userService;
        this.logService = logService;
    }

    /**
     * Gets accounts that user wants to buy and their summary price
     *@return map with accounts and their summary price or error message
     */
    @GetMapping("/checkout")
    public Map<String,Object> getBuyPage(){
        Map<String,Object> result = new HashMap<>();
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null) {
            String name = auth.getName();
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

    /**
     * Creates a PayPal payment and redirects user to payment URL or writes error message in log
     *@param sum summary price of purchase
     * @return redirecting link to PayPal paymnet URL
     */
    @GetMapping("/pay")
    public Map<String,String> payment(@RequestParam Long sum){
        Map<String,String> res = new HashMap<>();
        try {
            Order order = new Order();
            order.setMethod("paypal");
            order.setIntent("sale");
            order.setCurrency("RUB");

            order.setPrice(sum);
            order.setDescription("account purchase");
            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
            String name=auth.getName();
            User user= userService.findByUsername(name);
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
            StringBuilder json = new StringBuilder("{\"purchase\":\"").append(e.getMessage()).append("\"}");
            logService.writeLog(json.toString(),"PayPal exception");
        }
        return res;
    }


    /**
     * Marks bought accounts as SOLD,
     *@return message about operation status
     */
    @GetMapping("/pay/success")
    public Map<String,String> successPay(@RequestParam Map<String,String> allRequestParams){
        Map<String, String> result = new HashMap<>();
        try {
            Payment payment = paypalService.executePayment(allRequestParams.get("paymentId"),allRequestParams.get("PayerID"));
            logService.writeLog(payment.toJSON(), "PayPalPayment");
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
                    result.put("message","Успешно! Благодарим Вас за покупку!") ;
                    return result;
                }
            }
        }
        catch (PayPalRESTException e){
            StringBuilder json = new StringBuilder("{\"purchase\":\"").append(e.getMessage()).append("\"}");
            logService.writeLog(json.toString(),"PayPal exception");
        }
        result.put("message","Произошла ошибка на сервере") ;
        return result;
    }


}
