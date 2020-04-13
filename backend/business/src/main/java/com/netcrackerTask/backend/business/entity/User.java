package com.netcrackerTask.backend.business.entity;

import java.util.Set;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String activationCode;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;
    }

}