package com.netcrackerTask.backend.business.entity;

import java.util.Set;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    /**
     * User id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User name.
     */
    private String username;

    /**
     * User password.
     */
    private String password;

    /**
     * User email.
     */
    private String email;

    /**
     * User activation code.
     */
    private String activationCode;

    /**
     * User roles.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    /**
     * Constructor.
     */
    public User(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;
    }

}