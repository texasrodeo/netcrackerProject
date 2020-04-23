package com.netcrackerTask.backend.business.payloads;

import javax.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a request when accounts are paid and changes status in database.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellRequest {
    /**
     * PayPal payment id.
     */
    @NotBlank
    private String paymentId;

    /**
     * PayPal payer id.
     */
    @NotBlank
    private String payerId;

    /**
     * List of bought accounts id
     */
    @NotBlank
    private List<Long> accounts;

    /**
     * Payer Id in database
     */
    @NotBlank
    private Long userId;
}