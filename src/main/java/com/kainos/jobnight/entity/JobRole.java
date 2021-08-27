package com.kainos.jobnight.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

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

	@Column(name = "job_family_id")
	private String job_family_id;

	public String getUrl(){
		return url;
	}

	@Column(name = "responsibility_id")
	private short responsibility;

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="band_id", referencedColumnName = "band_id",
		insertable = false, updatable = false,
		foreignKey = @javax.persistence
			.ForeignKey(value = ConstraintMode.CONSTRAINT))
	private Band band;

	@ManyToMany
	@JoinTable(name = "responsibility_employee_role",
			joinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "role_id",
							nullable = false, updatable = false)},
			inverseJoinColumns = {
					@JoinColumn(name = "responsibility_id", referencedColumnName = "responsibility_id",
							nullable = false, updatable = false)})
	private Set<Responsibility> responsibilities;
	public JobRole(){}

	public JobRole(short id, String name, String specification, String url,
				   short responsibility, Band band, Set<Responsibility> responsibilities) {
		this.id = id;
		this.name = name;
		this.specification = specification;
		this.url = url;
		this.responsibility = responsibility;
		this.band = band;
		this.responsibilities = responsibilities;
	}

	public short getId() { return id; }
	public String getName() { return name; }
	public String getSpecification() { return specification; }

	public Band getBand() {
		return band;
	}

	public Set<Responsibility> getResponsibilities(){
		return this.responsibilities;
			}

	public String getJob_family_id() {
		return job_family_id;
	}

	public void setJob_family_id(String job_family_id) {
		this.job_family_id = job_family_id;
	}
}
