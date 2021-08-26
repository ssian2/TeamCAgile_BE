package com.kainos.jobnight.entity;

<<<<<<< HEAD
import javax.persistence.*;

@Entity
@Table(name = "capability")
public class Capability {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "capability_id")
	private short id;

	@Column(name = "capability_name")
	private String name;

	public Capability() {}
}
=======
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

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
    private List<JobRole> jobroles;

    public Capability(short ID, String name) {

        this.ID = ID;
        this.name = name;
    }

    public List<JobRole> getJobroles() {
        return jobroles;
    }

    public void setJobroles(List<JobRole> jobroles) {
        this.jobroles = jobroles;
    }

    public Capability(){
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
}
>>>>>>> 44a794b63817a5796884b3eb651c3e52f21d7dc0
