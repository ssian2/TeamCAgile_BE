package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "employee_role")
public class JobRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private short id;

	@Column(name = "role_name")
	private String name;

	@Column(name= "specification")
	private String specification;

	@ManyToOne
	@JoinColumn(name = "capability_id", nullable = false)
	@JsonBackReference
	private Capability capability;

	@Column(name = "band_id")
	private short band;

	@Column(name = "responsibility_id")
	private short responsibility;

	public JobRole(short id, String name, String specification, Capability capability, short band, short responsibility) {
		this.id = id;
		this.name = name;
		this.specification = specification;
		this.capability = capability;
		this.band = band;
		this.responsibility = responsibility;
	}

	public JobRole(){

	}

	public short getId() { return id; }
	public String getName() { return name; }
	public String getSpecification() { return specification; }

	public Capability getCapability() {
		return capability;
	}
	public void setCapability(Capability capability) {
		this.capability = capability;
	}
}
