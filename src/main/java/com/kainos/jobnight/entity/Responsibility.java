package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "responsibility")
@AllArgsConstructor
public class Responsibility {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "responsibility_id")
	private short id;

	@Column(name = "responsibility_name")
	private String name;

	@JsonBackReference
	@ManyToMany(mappedBy = "responsibilities")
	Set<JobRole> jobRoles;

	public short getId()
	{
		return this.id;
	}
	public String getName()
	{
		return this.name;
	}

	public Responsibility() {}

	
}
