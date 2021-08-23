package com.kainos.jobnight.entity;

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

	private String specification;

	@Column(name = "capability_id")
	private short capability;

	@Column(name = "band_id")
	private short band;

	@Column(name = "responsibility_id")
	private short responsibility;

	public JobRole() {}

	public short getId() { return id; }
	public String getName() { return name; }
}
