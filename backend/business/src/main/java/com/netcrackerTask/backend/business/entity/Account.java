package com.netcrackerTask.backend.business.entity;

import javax.persistence.*;

@Entity
@Table(name="game_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private Integer price;



    @Column(name="game_id")
    private Long gameId;

    public Long getGameId(){
        return  gameId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    //    private Integer status;
//
//    private Integer getStatus(){
//        return status;
//    }



//    public Account(Long id, String description, Integer price, Integer status, Long gameId){
//        this.id = id;
//        this.description = description;
//        this.price = price;
//        this.status = status;
//        this.gameId  = gameId;
//    }

    public Account() {

    }
}
