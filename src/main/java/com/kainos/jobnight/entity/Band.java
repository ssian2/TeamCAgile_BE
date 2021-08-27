package com.kainos.jobnight.entity;

import javax.persistence.*;

@Entity
@Table(name = "band")
public class Band implements Comparable<Band> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "band_id")
	private short id;

	@Column(name = "band_name", length = 30)
	private String name;

	public short getId() { return id; }
	public String getName() { return name; }

	@Override
	public int compareTo(Band o) {
		if (getName() == null || o.getName() == null) return 0;
		else return getName().compareTo(o.getName());
	}

	public Band() {}
}
