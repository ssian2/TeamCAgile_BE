package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


	@ManyToOne
	@JoinColumn(name="job_family_id")
	@JsonBackReference
	private JobFamily jobFamily;


	public String getUrl(){
		return url;
	}

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

	public short getId() { return id; }
	public String getName() { return name; }
	public String getSpecification() { return specification; }

	public Band getBand() {
		return band;
	}

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
