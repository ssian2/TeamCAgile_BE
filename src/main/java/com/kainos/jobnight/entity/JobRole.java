package com.kainos.jobnight.entity;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

>>>>>>> 44a794b63817a5796884b3eb651c3e52f21d7dc0
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

<<<<<<< HEAD
	private String specification;

	@Column(name = "capability_id")
	private short capability;
=======
	@Column(name= "specification")
	private String specification;

	@ManyToOne
	@JoinColumn(name = "capability_id", nullable = false)
	@JsonBackReference
	private Capability capability;
>>>>>>> 44a794b63817a5796884b3eb651c3e52f21d7dc0

	@Column(name = "band_id")
	private short band;

	@Column(name = "responsibility_id")
	private short responsibility;

<<<<<<< HEAD
	public JobRole() {}

	public short getId() { return id; }
	public String getName() { return name; }
=======
	public JobRole(short id, String name, String specification, Capability capability, short band, short responsibility) {
		this.id = id;
		this.name = name;
		this.specification = specification;
		this.capability = capability;
		this.band = band;
		this.responsibility = responsibility;
	}

	public JobRole(){

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
    
>>>>>>> 44a794b63817a5796884b3eb651c3e52f21d7dc0
}
