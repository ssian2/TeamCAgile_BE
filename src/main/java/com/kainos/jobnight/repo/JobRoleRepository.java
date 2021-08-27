package com.kainos.jobnight.repo;

import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.projections.JobRoleNameAndFamily;
import com.kainos.jobnight.projections.JobRoleWithBandandFamily;
import com.kainos.jobnight.projections.JobRoleWithBrandFamilyUrlAndSpec;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRoleRepository extends CrudRepository<JobRole, Short>
{/*
	@Query("SELECT `job_name`, `band_name` FROM `job_role` JOIN `band` USING(`band_id`)")
	List<JobRole> viewBandLevel();*/

	List<JobRole> findAll();
    
	Optional<JobRole> findById(Short id);


	@Query("Select j from JobRole j JOIN j.responsibilities ")
	public List<JobRole> testQuery();

	@Query("Select j from JobRole j")
	List<JobRoleNameAndFamily> jobRoleWithFamily();

	@Query("Select j from JobRole j")
	List<JobRoleWithBandandFamily> jobRoleWithBandAndFamily();

	@Query("Select role from JobRole role where role.id = ?1")
	List<JobRoleWithBrandFamilyUrlAndSpec> getJobRoleDetailsById(short id);

}

