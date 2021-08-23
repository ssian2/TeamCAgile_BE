package com.kainos.jobnight.entity;

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
