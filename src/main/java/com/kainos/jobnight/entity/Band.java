package com.kainos.jobnight.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "band")
public class Band implements Comparable<Band> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
}
