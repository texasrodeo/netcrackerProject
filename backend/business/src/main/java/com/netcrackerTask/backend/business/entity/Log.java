package com.netcrackerTask.backend.business.entity;

import java.sql.Timestamp;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Log{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data")
    private final Timestamp date;

    private final String type;

    private final String description;

}
