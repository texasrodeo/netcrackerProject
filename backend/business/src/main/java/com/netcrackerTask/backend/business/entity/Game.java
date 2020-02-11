package com.netcrackerTask.backend.business.entity;

import javax.persistence.*;

@Entity
@Table(name="game")
    public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        public Long getId(){
            return id;
        }

        private String description;

        public String getDescription(){
            return description;
        }

        private String name;

        public String getName(){
            return name;
        }

    }

