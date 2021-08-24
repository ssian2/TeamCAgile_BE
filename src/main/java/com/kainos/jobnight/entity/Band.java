package com.kainos.jobnight.entity;

import javax.persistence.*;

@Entity
@Table(name = "band")
public class Band {

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "band_id")
	private short id;

	@Column(name = "band_name")
	private String name;

	public Band() {}
}
