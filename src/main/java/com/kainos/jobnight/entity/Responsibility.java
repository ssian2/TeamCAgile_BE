package com.kainos.jobnight.entity;

import javax.persistence.*;

@Entity
@Table(name = "responsibility")
public class Responsibility {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "responsibility_id")
	private short id;

	public Responsibility() {}
}
