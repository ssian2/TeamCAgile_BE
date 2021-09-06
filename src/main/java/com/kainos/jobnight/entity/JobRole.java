package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	public JobRole(short id, String name, String specification, String url, JobFamily jobFamily,
				   Band band, Set<Responsibility> responsibilities) {
		this.id = id;
		this.name = name;
		this.specification = specification;
		this.url = url;
		this.jobFamily = jobFamily;
		this.band = band;
		this.responsibilities = responsibilities;
	}

	public JobRole(short id, String name, String spec, String url)	
	{
		this.id = id;
		this.name = name;
		this.specification = specification;
		this.url = url;
	}

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

	public String getBandName() {
		return getBand().getName();
	}

	public void setId(short id) {
		this.id = id;
	}

	public JobFamily getJobFamily() {
		return jobFamily;
	}

	public void setJobFamily(JobFamily jobFamily) {
		this.jobFamily = jobFamily;
	}

	public String getJobFamilyName(){
		return this.getJobFamily().getName();
	}

	public String getCapability(){
		return this.getJobFamily().getCapabilityName();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setResponsibilities(Set<Responsibility> responsibilities) {
		this.responsibilities = responsibilities;
	}

	public Set<Responsibility> getResponsibilities(){
		return this.responsibilities;
	}

	public List<String> getResponsibilitiesList(){
		return getResponsibilities().stream().map(Responsibility::getName).collect(Collectors.toList());
	}
}
