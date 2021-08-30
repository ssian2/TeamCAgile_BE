package com.kainos.jobnight.repo;

import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.entity.Competency;
import com.kainos.jobnight.projections.BandWithCompetencies;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompetencyRepository extends CrudRepository<Competency, Short> {
	List<Competency> findAll();

	@Query("select c from Competency c")
	List<BandWithCompetencies> getBandWithCompetencies();
}
