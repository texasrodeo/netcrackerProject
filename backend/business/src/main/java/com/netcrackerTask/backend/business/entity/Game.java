package com.netcrackerTask.backend.business.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Game entity.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "game")
public class Game {
    /**
     * Game id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Game description.
     */
    private String description;

    /**
     * Game name.
     */
    private String name;

}

