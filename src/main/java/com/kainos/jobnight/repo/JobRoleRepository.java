package com.kainos.jobnight.repo;

import com.kainos.jobnight.entity.JobRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRoleRepository extends CrudRepository<JobRole, Short>
{

	@Query("select c from JobRole c join c.band_name")
	public List<JobRole> viewBandLevel();

	List<JobRole> findAll();
    
	Optional<JobRole> findById(Short id);

	@Query("Select j from JobRole j JOIN j.responsibilities ")
	public List<JobRole> testQuery();
}