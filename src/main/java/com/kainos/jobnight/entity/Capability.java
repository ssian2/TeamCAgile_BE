package com.kainos.jobnight.entity;


import javax.persistence.*;
import javax.websocket.OnError;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Table(name="capability")
@NoArgsConstructor
@AllArgsConstructor
public class Capability {
    @Id
    @GeneratedValue
    @Column(name = "capability_id")
    private short ID;
    @Column(name = "capability_name")
    private String name;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "capability", fetch = FetchType.EAGER)
    private Set<JobFamily> jobFamilies;

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

    public Set<JobFamily> getJobFamilies() { return this.jobFamilies; }
    public void setJobFamilies(Set<JobFamily> families) { this.jobFamilies = families; }

    @Override
    public String toString() {
        return String.format("{ID:%d, name:\"%s\", jobFamilies:%s}", ID, name, jobFamilies);
    }
}
