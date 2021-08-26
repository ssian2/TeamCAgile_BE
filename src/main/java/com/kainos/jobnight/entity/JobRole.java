package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

	@Column(name = "specification")
	private String specification;
	
	@Column(name = "spec_doc_url")
	private String url;

	public String getUrl(){
		return url;
	}

	@ManyToOne
	@JoinColumn(name = "capability_id", nullable = false)
	@JsonBackReference
	private Capability capability;

	@Column(name = "responsibility_id")
	private short responsibility;

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="band_id", referencedColumnName = "band_id",
		insertable = false, updatable = false,
		foreignKey = @javax.persistence
			.ForeignKey(value = ConstraintMode.CONSTRAINT))
	private Band band;

	public JobRole(){}

	public JobRole(short id, String name, String specification, Capability capability, Band band, short responsibility) {
		this.id = id;
		this.name = name;
		this.specification = specification;
		this.capability = capability;
		this.band = band;
		this.responsibility = responsibility;
	}

	public short getId() { return id; }
	public String getName() { return name; }
	public String getSpecification() { return specification; }

	public Capability getCapability() {
		return capability;
	}
	public void setCapability(Capability capability) {
		this.capability = capability;
	}

	public Band getBand() {
		return band;
	}
}
