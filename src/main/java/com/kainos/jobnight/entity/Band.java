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

	@Column(name = "band_level", length = 30)
	private int band_level;

	public short getId() { return id; }
	public String getName() { return name; }

	@Override
	public int compareTo(Band o) {
		if (getName() == null || o.getName() == null) return 0;
		else return getName().compareTo(o.getName());
	}

	// This checks for ID equality only
	// Different names will not be accounted for
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Band) {
			Band c = (Band) obj;
			return getId() == c.getId();
		}
		return false;
	}

	@Override
	public String toString() {
		return name;
	}

	public Band() {}

	public Band(String name, Short id) {
		this.name = name;
		this.id = id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBand_level() {
		return band_level;
	}

	public void setBand_level(int band_level) {
		this.band_level = band_level;
	}
}
