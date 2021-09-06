package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.JSONPropertyName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="job_family")
@AllArgsConstructor
@NoArgsConstructor
public class JobFamily {
    @Id
    @GeneratedValue
    @Column(name = "job_family_id")
    private long ID;

    @Column(name = "job_family_name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "jobFamily")
    private List<JobRole> jobroles;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "capability_id")
    private Capability capability;

    @JsonManagedReference
    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "jobFamily",
        fetch = FetchType.EAGER,
        targetEntity = JobRole.class)
    private Set<JobRole> jobRoles;

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

    public void setCapability(Capability capability) {
        this.capability = capability;
    }

    @JsonProperty("capability_name")
    public String getCapabilityName() { return this.capability.getName(); }

    public Capability getCapability() {
        return capability;
    }

    @JsonGetter(value="jobRoles")
    public Set<JobRole> getJobRoles() { return jobRoles; }
    public void setJobRoles(Set<JobRole> roles) { this.jobRoles = roles; }

    @Override
    public String toString() {
        return String.format("ID:%d, name:\"%s\", capability:%s", ID, name, capability.getName());
    }

    public List<String> getJobRolesNames(){
        return getJobroles().stream().map(JobRole::getName).distinct().collect(Collectors.toList());
    }
}
