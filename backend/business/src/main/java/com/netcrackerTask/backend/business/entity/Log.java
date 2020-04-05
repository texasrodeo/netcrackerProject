package com.netcrackerTask.backend.business.entity;


import com.google.gson.JsonObject;

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



}
