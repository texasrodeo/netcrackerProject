/*
 * Copyright (c)
 */

package com.netcrackerTask.backend.business.payloads;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayRequest {
    @NotBlank
    private String sum;
}
