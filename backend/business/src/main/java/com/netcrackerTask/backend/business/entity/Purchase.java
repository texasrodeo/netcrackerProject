package com.netcrackerTask.backend.business.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Purchase entity.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase")
public class Purchase {

    /**
     * Purchase id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Purchase date.
     */
    @Column(name="data")
    private Timestamp date;

    /**
     * Purchase status (CONFIRMED, ADDED_TO_BASKET).
     */
    @Column(name="status")
    private String status;

    /**
     * Purchase owner id.
     */
    @Column(name="user_id")
    private Long userId;

    /**
     *Game account id in purchase.
     */
    @Column(name="game_account_id")
    private Long gameAccountId;

    /**
     * Constructor.
     */
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
