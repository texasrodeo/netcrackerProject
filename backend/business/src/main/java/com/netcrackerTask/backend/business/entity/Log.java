package com.netcrackerTask.backend.business.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;



@Entity

public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="data")
    private Timestamp date;

    @Column(name="type")
    private String type;

    @Column(name="json")
    private String json;

    public Log(Timestamp date, String type, String json) {
        this.date = date;
        this.type = type;
        this.json = json;
    }
}
