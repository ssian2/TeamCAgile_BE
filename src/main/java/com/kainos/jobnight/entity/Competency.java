package com.kainos.jobnight.entity;

import javax.persistence.*;

@Entity
@Table(name = "competency")
public class Competency {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competency_id")
	private byte id;

	@ManyToOne(fetch=FetchType.EAGER, optional = false)
	@JoinColumn(name="competency_type_id", nullable=false)
	private CompetencyType competencyType;

	@ManyToOne(fetch=FetchType.EAGER, optional = false)
	@JoinColumn(name="band_id", nullable=false)
	private Band band;

	@Column(name="competency_description", length=300)
	private String description;

	public short getId() { return id; }
	public CompetencyType getCompetency() { return competencyType; }
	public Band getBand() { return band; }
	public String getDescription() { return description; }
}
