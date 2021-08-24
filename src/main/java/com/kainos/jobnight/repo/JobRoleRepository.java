package com.kainos.jobnight.repo;

import com.kainos.jobnight.entity.JobRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRoleRepository extends CrudRepository<JobRole, Short>
{
	//JobRole findById(short id);
	List<JobRole> findAll();

//	@Query("select id, name, specification from JobRole")
//	List<JobRole> viewJobSpec();

	Optional<JobRole> findById(Short id);
}
