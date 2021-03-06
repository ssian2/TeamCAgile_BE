package com.kainos.jobnight.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "band")
@AllArgsConstructor
public class Band implements Comparable<Band> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "band_id")
	private short id;

	@Column(name = "band_name", length = 30)
	private String name;

	@ManyToMany
	@JoinTable(name = "band_training",
			joinColumns = {
					@JoinColumn(name = "band_id", referencedColumnName = "band_id",
							nullable = false, updatable = true)},
			inverseJoinColumns = {
					@JoinColumn(name = "training_id", referencedColumnName = "training_id",
							nullable = false, updatable = true)})				
	private Set<Training> trainings;

	public short getId() { return id; }
	public String getName() { return name; }
	public Set<Training> getTrainings(){
		return this.trainings;
	}

	@Column(name = "band_level", length = 30)
	private int band_level;

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
