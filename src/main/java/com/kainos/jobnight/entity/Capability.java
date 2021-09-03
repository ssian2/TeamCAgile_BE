package com.kainos.jobnight.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;


@Entity
@Table(name="capability")
@AllArgsConstructor
public class Capability {
    @Id
    @GeneratedValue
    @Column(name = "capability_id")
    private short ID;
    @Column(name = "capability_name")
    private String name;

    public long getID() {
        return ID;
    }

    public void setID(short ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
