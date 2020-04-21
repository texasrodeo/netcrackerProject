package com.netcrackerTask.backend.business.service.interfaces;

import java.util.List;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface IPaypalService {
    /**
     * Returns PayPal payment object
     * @param total purchase summary price
     * @param currency purchase currency (default="RUB")
     * @param method payment method (PayPal)
     * @param intent payment goal (sale)
     * @param description payment description
     * @param cancelUrl url to redirect if payment fails
     * @param successUrl url to redirect if payment successes
     * @return payment object
     * */
    Payment createPayment(Double total, String currency,
            String method, String intent,
            String description, String cancelUrl,
            String successUrl
    ) throws PayPalRESTException;

    /**
     * Executes payment
     * @param payerId user who pays dd
     * @param paymentId payment id
     * @throws PayPalRESTException if payment executes with error
     * @return user
     * */
    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

    /**
     * Completes successful payment url
     * @param accounts list of accounts bought
     * @param success url
     * @return url
     * */
    String completeSuccessUrl(List<Long> accounts, String success);
}
