package com.kainos.jobnight.repo;

import com.kainos.jobnight.entity.Competency;
import com.kainos.jobnight.projections.band.BandWithCompetencies;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompetencyRepository extends CrudRepository<Competency, Byte> {
	List<Competency> findAll();

	@Query("select c from Competency c")
	List<BandWithCompetencies> getBandWithCompetencies();

	@Query("SELECT c from Competency c where c.band.id = ?1")
    List<BandWithCompetencies> getBandWithCompetenciesByID(Short id);
}
