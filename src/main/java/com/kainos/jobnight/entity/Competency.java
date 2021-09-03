package com.kainos.jobnight.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "competency")
public class Competency {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competency_id")
	private byte id;

	@JsonManagedReference
	@ManyToOne/*(fetch=FetchType.LAZY)*/
	@JoinColumn(name="competency_type_id")
	private CompetencyType competencyType;

	@JsonManagedReference
	@ManyToOne/*(fetch=FetchType.LAZY)*/
	@JoinColumn(name="band_id")
	private Band band;

	@Column(name="competency_description", length=300)
	private String description;

	public short getId() { return id; }
	public CompetencyType getCompetency() { return competencyType; }
	public Band getBand() { return band; }
	public String getDescription() { return description; }
}
