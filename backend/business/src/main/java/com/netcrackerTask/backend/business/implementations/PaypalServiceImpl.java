package com.netcrackerTask.backend.business.implementations;

import com.netcrackerTask.backend.business.service.interfaces.IPaypalService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalServiceImpl implements IPaypalService {


    private final APIContext context;

    /**
     * Constructor.
     * @param apiContext PayPal context
     */
    @Autowired
    public PaypalServiceImpl(final APIContext apiContext){
        this.context = apiContext;
    }

    public Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl
    ) throws PayPalRESTException{
        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(total.toString());  

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactionList);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        payment.setRedirectUrls(redirectUrls);

        try{
            return payment.create(context);
        }

        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        return payment.execute(context, paymentExecution);
    }

    public String completeSuccessUrl(List<Long> accounts, String success){
        StringBuilder sb = new StringBuilder(success);
        sb.append("?id0=").append(accounts.get(0));
        for(int i=1;i<accounts.size();i++){
            sb.append("&id").append(i).append("=").append(accounts.get(i));
        }
        return sb.toString();
    }
}
