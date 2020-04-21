package com.netcrackerTask.backend.business.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game_account")
public class Account {
    /**
     * Account id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Account description.
     */
    private String description;

    /**
     * Account price.
     */
    private Integer price;

    /**
     * Account status (SOLD, FREE).
     */
    private String status;

    /**
     * Account login.
     */
    private String login;

    /**
     * Account password.
     */
    private String password;

    /**
     * Account game_id.
     */
    @Column(name = "game_id")
    private Long gameId;

}
