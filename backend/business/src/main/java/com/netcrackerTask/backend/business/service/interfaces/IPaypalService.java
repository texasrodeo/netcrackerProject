package com.netcrackerTask.backend.business.service.interfaces;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.util.List;

public interface IPaypalService {
    Payment createPayment(Double total, String currency,
            String method, String intent,
            String description, String cancelUrl,
            String successUrl
    ) throws PayPalRESTException;

    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
    String completeSuccessUrl(List<Long> accounts, String success);
}
