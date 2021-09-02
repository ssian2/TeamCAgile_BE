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
    @Column(name = "lead_name")
    private String leadName;
    @Column(name = "lead_photo")
    private String leadPhoto;
    @Column(name = "lead_message")
    private String leadMessage;

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

    public Capability(short ID, String name) {
        this.ID = ID;
        this.name = name;
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


    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getLeadPhoto() {
        return leadPhoto;
    }

    public void setLeadPhoto(String leadPhoto) {
        this.leadPhoto = leadPhoto;
    }

    public String getLeadMessage() {
        return leadMessage;
    }

    public void setLeadMessage(String leadMessage) {
        this.leadMessage = leadMessage;
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
  
    public List<List<String>> getJobRolesInCapability(){
        return getJobFamilies().stream().map(JobFamily::getJobRolesNames).filter(x -> !x.isEmpty()).collect(Collectors.toList());
    }
}
