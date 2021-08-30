package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="capability")
public class Capability {
    @Id
    @GeneratedValue
    @Column(name = "capability_id")
    private short ID;
    @Column(name = "capability_name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "capability")
    private List<JobFamily> jobFamilies;

    public Capability() {

    }

    public Capability(short ID, String name, List<JobFamily> jobFamilies) {
        this.ID = ID;
        this.name = name;
        this.jobFamilies = jobFamilies;
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

    public List<JobFamily> getJobFamilies() {
        return jobFamilies;
    }

    public void setJobFamilies(List<JobFamily> jobFamilies) {
        this.jobFamilies = jobFamilies;
    }

    public List<String> getJobFamiliesNames(){
        return getJobFamilies().stream().map(JobFamily::getName).collect(Collectors.toList());
    }

}
