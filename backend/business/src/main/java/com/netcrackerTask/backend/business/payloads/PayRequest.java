package com.netcrackerTask.backend.business.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

/**
 * This is a request when user buys accounts.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayRequest {
    /**
     * summary price of user purchase.
     */
    @NotBlank
    private String sum;
}
