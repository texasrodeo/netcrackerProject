package com.netcrackerTask.backend.business.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="data")
    private Timestamp date;


    @Column(name="status")
    private String status;


    @Column(name="user_id")
    private Long userId;

    @Column(name="game_account_id")
    private Long gameAccountId;


    public Purchase(Timestamp date, String status, Long userId, Long gameAccountId){
        this.date = date;
        this.status = status;
        this.userId = userId;
        this.gameAccountId  = gameAccountId;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("id = ").append(id.toString())
            .append(", date = ").append(date.toString()).append(", client_id = ")
            .append(userId.toString()).append(", account_id = ").append(gameAccountId.toString())
            .append(", status = ").append(status.toString());
        return sb.toString();
    }


}
