package com.netcrackerTask.backend.business.entity;

import java.sql.Timestamp;
import javax.persistence.*;
import lombok.Data;

/**
 * Log entity.
 */
@Entity
@Data
public class Log{
    /**
     * Log id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Log date of creation.
     */
    @Column(name = "data")
    private final Timestamp date;

    /**
     * Log type (PayPal payment, PayPal error, successful sale etc.).
     */
    private final String type;

    /**
     * Json file where thi log info is given.
     */
    private final String json;

}
