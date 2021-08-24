package com.kainos.jobnight.model;

import javax.persistence.*;

@Entity
@Table(name="capability")
public class Capability {
    @Id
    @GeneratedValue

    @Column(name = "capability_id")
    private short ID;
    @Column(name = "capability_name")
    private String name;

    public Capability(short ID, String name) {

        this.ID = ID;
        this.name = name;
    }

    public Capability(){
    }

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