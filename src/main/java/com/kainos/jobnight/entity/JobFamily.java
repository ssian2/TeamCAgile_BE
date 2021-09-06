package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    public long getID() { return ID; }

    public void setID(long ID) { this.ID = ID; }

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
}
