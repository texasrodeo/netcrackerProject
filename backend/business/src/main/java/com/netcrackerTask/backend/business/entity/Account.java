package com.netcrackerTask.backend.business.entity;

public class Account {
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

    private Long game_id;

    public Long getGameId(){
        return  game_id;
    }

    public Account(Long id, String description, Integer price, Integer status, Long gameId){
        this.id = id;
        this.description = description;
        this.price = price;
        this.status = status;
        this.game_id  = gameId;
    }

    public Account() {

    }
}
