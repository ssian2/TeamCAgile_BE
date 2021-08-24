package com.kainos.jobnight.entity;

import javax.persistence.*;

@Entity
@Table(name = "band")
public class Band {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "band_id")
	private short id;

	@Column(name = "band_name")
	private String name;

	public Band() {}
}
