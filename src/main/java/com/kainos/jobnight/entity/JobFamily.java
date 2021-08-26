package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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

    @ManyToOne
    @JoinColumn(name = "capability_id", nullable = false)
    @JsonBackReference
    private Capability capability;

    @Transient
    @JsonSerialize
    private String capability_name;

    public JobFamily(short ID, String name, Capability capability) {
        this.ID = ID;
        this.name = name;
        this.capability = capability;
        this.capability_name = capability.getName();
    }

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

    public void setCapability(Capability capability) {
        this.capability = capability;
    }

    public Capability getCapability() {
        return capability;
    }

    public String getCapability_name() {
        return getCapability().getName();
    }

    public void setCapability_name(String capability_name) {
         this.capability_name = this.getCapability().getName();
    }
}
