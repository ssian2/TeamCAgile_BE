package com.kainos.jobnight.entity;

import javax.persistence.*;

@Entity
@Table(name = "band")
<<<<<<< HEAD
public class Band implements Comparable<Band> {
=======
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

>>>>>>> 44a794b63817a5796884b3eb651c3e52f21d7dc0
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "band_id")
	private short id;

<<<<<<< HEAD
	@Column(name = "band_name", length = 30)
	private String name;

	public Band() {}

	public short getId() { return id; }
	public String getName() { return name; }

	@Override
	public int compareTo(Band o) {
		if (getName() == null || o.getName() == null) return 0;
		else return getName().compareTo(o.getName());
	}
=======
	@Column(name = "band_name")
	private String name;

	public Band() {}
>>>>>>> 44a794b63817a5796884b3eb651c3e52f21d7dc0
}
