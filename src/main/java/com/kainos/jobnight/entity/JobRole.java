package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee_role")
@AllArgsConstructor
public class JobRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private short id;

	@Column(name = "role_name")
	private String name;

	@Column(name = "specification")
	private String specification;
	
	@Column(name = "spec_doc_url")
	private String url;

	@JsonManagedReference
	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name = "band_id")
	private Band band;

	//TODO: Apparently using @JoinTable on entities is bad practice,
	//TODO: Consider using @JoinColumn instead?
	@ManyToMany
	@JoinTable(name = "responsibility_employee_role",
		joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "role_id",
				nullable = false, updatable = false)},
		inverseJoinColumns = {
			@JoinColumn(name = "responsibility_id", referencedColumnName = "responsibility_id",
				nullable = false, updatable = false)})
	private Set<Responsibility> responsibilities;

	@JsonBackReference
	@ManyToOne(
		fetch = FetchType.EAGER,
		optional = false,
		targetEntity = JobFamily.class
	)
	@JoinColumn(name="job_family_id")
	private JobFamily jobFamily;

	public JobRole(){}

	public short getId() { return id; }
	// id is auto-generated, no setter needed/allowed

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getSpecification() { return specification; }
	public void setSpecification(String specification) { this.specification = specification; }

	public String getUrl(){
		return url;
	}

	public Band getBand() { return band; }
	public void setBand(Band band) { this.band = band; }

	public JobFamily getJobFamily() { return jobFamily; }
	public void setJobFamily(JobFamily family) { this.jobFamily = family; }

	public Set<Responsibility> getResponsibilities(){
		return this.responsibilities;
	}
}
