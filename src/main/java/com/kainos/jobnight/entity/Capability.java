package com.kainos.jobnight.entity;


import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="capability")
@NoArgsConstructor
@AllArgsConstructor
public class Capability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "capability_id")
    private short ID;
    @Column(name = "capability_name")
    private String name;

    //@JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "capability", fetch = FetchType.EAGER)
    private Set<JobFamily> jobFamilies;

    @Column(name = "lead_name")
    private String leadName;
    @Column(name = "lead_photo")
    private String leadPhoto;
    @Column(name = "lead_message")
    private String leadMessage;

    public Capability(short ID, String name, Set<JobFamily> jobFamilies) {
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

    @JsonGetter("jobroles")
    public Set<JobRole> getJobRoles() {
        Set<JobRole> s = new HashSet<>();

        for (JobFamily family : jobFamilies) {
            s.addAll(family.getJobRoles());
        }

        return s;
    }

    @Override
    public String toString() {
        return String.format("{ID:%d, name:\"%s\", jobFamilies:%s}", ID, name, jobFamilies);
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

    public Set<JobFamily> getJobFamilies() {
        return jobFamilies;
    }

    public void setJobFamilies(Set<JobFamily> jobFamilies) {
        this.jobFamilies = jobFamilies;
    }

    public List<String> getJobFamiliesNames(){
        return getJobFamilies().stream().map(JobFamily::getName).collect(Collectors.toList());
    }
  
    public List<List<String>> getJobRolesInCapability(){
        return getJobFamilies().stream().map(JobFamily::getJobRolesNames).filter(x -> !x.isEmpty()).collect(Collectors.toList());
    }
}
