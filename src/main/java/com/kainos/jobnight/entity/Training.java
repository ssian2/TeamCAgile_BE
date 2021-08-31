package com.kainos.jobnight.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "training")
public class Training {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "training_id")
	private short id;

	@Column(name = "name")
	private String name;

    @Column(name = "type")
	private String training_type;

	@Column(name = "description")
	private String description;

	@Column(name = "training_url")
	private String url;
	
	@ManyToMany(mappedBy = "trainings")
	Set<Band> Bands;

	public String getName(){
		return this.name;
	}

	public String getType(){
		return this.training_type;
	}

	public String getDescription() {
		return this.description;
	}

	public String getUrl() {
		return url;
	}
}