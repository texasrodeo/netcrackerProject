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
public class AddAccRequest {

    @NotBlank
    private String gameId;

    @NotBlank
    private String description;

    @NotBlank
    private String price;

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
