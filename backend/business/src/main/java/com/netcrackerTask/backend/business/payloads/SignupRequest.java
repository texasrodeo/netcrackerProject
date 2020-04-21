package com.netcrackerTask.backend.business.payloads;

import java.util.Set;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a request when user signs up.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    /**
     * user name.
     */
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    /**
     * user email.
     */
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    /**
     * user role.
     */
    private Set<String> role;

    /**
     * user password.
     */
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
