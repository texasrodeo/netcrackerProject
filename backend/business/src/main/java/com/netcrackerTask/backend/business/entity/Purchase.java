package com.netcrackerTask.backend.business.entity;

import java.util.Date;

public class Purchase {

    private Long id;

    public Long getId(){
        return id;
    }

    private Date date;

    public Date getDate(){
        return date;
    }

    private Integer status;

    public Integer getStatus(){
        return status;
    }



    private Long user_id;

    public Long getGameId(){
        return  user_id;
    }

    private Long game_account_id;

    public Long getGameAccountId(){
        return  game_account_id;
    }

    public Purchase(Long id, Date date, Integer status, Long userId, Long gameAccountId){
        this.id = id;
        this.date = date;
        this.status = status;
        this.user_id = userId;
        this.game_account_id  = gameAccountId;
    }

    public Purchase() {

    }

}
