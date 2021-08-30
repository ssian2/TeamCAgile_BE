package com.kainos.jobnight.repo;

import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.projections.band.BandNames;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BandRepository  extends CrudRepository<Band, Short> {
	List<Band> findAll();

	@Query("select bands from Band bands order by bands.band_level")
	List<BandNames> findAllOrderByBandLevel();
}
