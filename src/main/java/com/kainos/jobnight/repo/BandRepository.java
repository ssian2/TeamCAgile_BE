package com.kainos.jobnight.repo;

import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.projections.BandAndTrainings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BandRepository  extends CrudRepository<Band, Short> {
	List<Band> findAll();

	@Query("Select b from Band b JOIN b.trainings")
	List<BandAndTrainings> getBandsAndTrainigs();
}
