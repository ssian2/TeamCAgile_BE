package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Entity
@Table(name="job_family")
@AllArgsConstructor
@NoArgsConstructor
public class JobFamily {
    @Id
    @GeneratedValue
    @Column(name = "job_family_id")
    private short ID;

    @Column(name = "job_family_name")
    private String name;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "capability_id")
    private Capability capability;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobFamily", fetch = FetchType.EAGER)
    private Set<JobRole> jobRoles;

    public short getID() { return ID; }

    public void setID(short ID) { this.ID = ID; }

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

    public Set<JobRole> getJobRoles() { return jobRoles; }
}
