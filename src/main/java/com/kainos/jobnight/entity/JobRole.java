package com.kainos.jobnight.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "employee_role")
public class JobRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private short id;

	@Column(name = "role_name")
	private String name;

	private String specification;

	@Column(name = "capability_id")
	private short capability;

	@Column  (name = "band_id")
	private short band;

	@Column(name = "responsibility_id")
	private short responsibility;

	public Band getBand_name() {
		return band_name;
	}

	public void setBand_name(Band band_name) {
		this.band_name = band_name;
	}

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="band_id", referencedColumnName = "band_id",
			insertable = false, updatable = false,
			foreignKey = @javax.persistence
					.ForeignKey(value = ConstraintMode.CONSTRAINT))
	private Band band_name;

	public JobRole() {}

	public short getId() { return id; }
	public String getName() { return name; }
}
