package com.kainos.jobnight.repo;

import com.kainos.jobnight.entity.JobRole;
<<<<<<< HEAD
=======
import org.springframework.data.jpa.repository.Query;
>>>>>>> 44a794b63817a5796884b3eb651c3e52f21d7dc0
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> 44a794b63817a5796884b3eb651c3e52f21d7dc0

@Repository
public interface JobRoleRepository extends CrudRepository<JobRole, Short>
{
<<<<<<< HEAD
	//JobRole findById(short id);
	List<JobRole> findAll();
}
=======

	@Query("select c from JobRole c join c.band_name")
	public List<JobRole> viewBandLevel();

	List<JobRole> findAll();
    
	Optional<JobRole> findById(Short id);
}
>>>>>>> 44a794b63817a5796884b3eb651c3e52f21d7dc0
