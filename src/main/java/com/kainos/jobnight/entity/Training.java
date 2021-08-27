package com.kainos.jobnight.entity;

import javax.persistence.*;

@Entity
@Table(name = "training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "training_id")
    private byte id;

    @Column(name="name", length=100)
    private String name;

    @Column(name="type", length=100)
    private String type;

    @Column(name="description", length=300)
    private String description;

    public byte getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
}
