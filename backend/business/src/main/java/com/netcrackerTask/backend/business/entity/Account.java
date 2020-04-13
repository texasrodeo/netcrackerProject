package com.netcrackerTask.backend.business.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game_account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private Integer price;

    private String status;

    private String login;

    private String password;

    @Column(name = "game_id")
    private Long gameId;

}
