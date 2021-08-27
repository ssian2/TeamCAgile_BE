package com.kainos.jobnight.repo;

import com.kainos.jobnight.entity.Band;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BandRepository  extends CrudRepository<Band, Short> {
	List<Band> findAll();
}
