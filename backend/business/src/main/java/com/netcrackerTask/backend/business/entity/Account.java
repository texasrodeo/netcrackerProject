package com.netcrackerTask.backend.business.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="game_account")
public class  Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private Integer price;

    private String status;

    private String login;

    private String password;

    @Column(name="game_id")
    private Long gameId;


}
