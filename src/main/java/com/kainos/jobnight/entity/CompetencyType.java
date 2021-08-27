package com.kainos.jobnight.entity;

import javax.persistence.*;

@Entity
@Table(name="competency_type")
public class CompetencyType {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="competency_type_id")
	private byte id;

	@Column(name="competency_type_name", length=50)
	private String name;

	public byte getId() { return id; }
	public String getName() { return name; }
}
