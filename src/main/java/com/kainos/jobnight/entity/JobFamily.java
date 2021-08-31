package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="job_family")
public class JobFamily {
    @Id
    @GeneratedValue
    @Column(name = "job_family_id")
    private short ID;

    @Column(name = "job_family_name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "jobFamily")
    private List<JobRole> jobroles;

    @ManyToOne
    @JoinColumn(name="capability_id")
    @JsonBackReference
    private Capability capability;

    public JobFamily() {

    }

    public JobFamily(short ID, String name, List<JobRole> jobroles, Capability capability) {
        this.ID = ID;
        this.name = name;
        this.jobroles = jobroles;
        this.capability = capability;
    }

    public List<JobRole> getJobroles() {
        return jobroles;
    }

    public void setJobroles(List<JobRole> jobroles) {
        this.jobroles = jobroles;
    }

    public Capability getCapability() {
        return capability;
    }
    public String getCapabilityName() {
        return getCapability().getName();
    }

    public void setCapability(Capability capability) {
        this.capability = capability;
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
