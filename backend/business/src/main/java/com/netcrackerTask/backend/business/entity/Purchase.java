package com.netcrackerTask.backend.business.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId(){
        return id;
    }

    @Column(name="date")
    private Timestamp date;

    public Timestamp getDate(){
        return date;
    }

    @Column(name="status")
    private Integer status;

    public Integer getStatus(){
        return status;
    }



    @Column(name="user_id")
    private Long userId;

    public Long getGameId(){
        return  userId;
    }

    @Column(name="game_account_id")
    private Long gameAccountId;

    public Long getGameAccountId(){
        return  gameAccountId;
    }

    public Purchase(Timestamp date, Integer status, Long userId, Long gameAccountId){
        this.date = date;
        this.status = status;
        this.userId = userId;
        this.gameAccountId  = gameAccountId;
    }

    public Purchase() {

    }

}
