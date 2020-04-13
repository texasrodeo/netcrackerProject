/*
 * Copyright (c)
 */

package com.netcrackerTask.backend.business.service.interfaces;

import java.util.List;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface IPayPalService {
    Payment createPayment(Double total, String currency,
                          String method, String intent,
                          String description, String cancelUrl,
                          String successUrl
    ) throws PayPalRESTException;

    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

    String completeSuccessUrl(List<Long> accounts, String success);
}
