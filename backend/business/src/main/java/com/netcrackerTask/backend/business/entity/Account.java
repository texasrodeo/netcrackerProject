package com.netcrackerTask.backend.business.entity;

import javax.persistence.*;

@Entity
@Table(name="game_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId(){
        return id;
    }

    private String description;

    public String getDescription(){
        return description;
    }

    private Integer price;

    public Integer getPrice(){
        return price;
    }

    private Integer status;

    private Integer getStatus(){
        return status;
    }

    @Column(name="game_id")
    private Long gameId;

    public Long getGameId(){
        return  gameId;
    }

    public Account(Long id, String description, Integer price, Integer status, Long gameId){
        this.id = id;
        this.description = description;
        this.price = price;
        this.status = status;
        this.gameId  = gameId;
    }

    public Account() {

    }
}
