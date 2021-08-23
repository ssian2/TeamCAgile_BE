package com.kainos.jobnight.repo;

import com.kainos.jobnight.entity.JobRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRoleRepository extends CrudRepository<JobRole, Short>
{
	//JobRole findById(short id);
	List<JobRole> findAll();
}
