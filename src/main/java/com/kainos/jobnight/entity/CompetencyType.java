package com.kainos.jobnight.entity;

import javax.persistence.*;

@Entity
@Table(name="competency_type")
public class CompetencyType{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="competency_type_id")
	private byte id;

	@Column(name="competency_type_name", length=50)
	private String name;

	public byte getId() { return id; }
	public String getName() { return name; }

	// This checks for ID equality only
	// Different names will not be accounted for
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CompetencyType) {
			CompetencyType c = (CompetencyType) obj;
			return getId() == c.getId();
		}
		return false;
	}

	@Override
	public String toString() {
		return name;
	}
}
