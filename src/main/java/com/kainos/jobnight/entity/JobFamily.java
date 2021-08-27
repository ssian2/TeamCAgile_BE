package com.kainos.jobnight.entity;

import javax.persistence.*;

@Entity
@Table(name="job_family")
public class JobFamily {
    @Id
    @GeneratedValue
    @Column(name = "job_family_id")
    private short ID;

    @Column(name = "job_family_name")
    private String name;


    public JobFamily() {

    }

    public short getID() {
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
