package com.netcrackerTask.backend.business.payloads;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a request when admin adds account.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAccRequest {
    /**
     * Account game id.
     */
    @NotBlank
    private String gameId;

    /**
     * Account description.
     */
    @NotBlank
    private String description;

    /**
     * Account price.
     */
    @NotBlank
    private String price;

    /**
     * Account login.
     */
    @NotBlank
    private String login;

    /**
     * Account password.
     */
    @NotBlank
    private String password;
}
