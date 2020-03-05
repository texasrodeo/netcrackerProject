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

    private String status;

    private String login;

    private String password;

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

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
