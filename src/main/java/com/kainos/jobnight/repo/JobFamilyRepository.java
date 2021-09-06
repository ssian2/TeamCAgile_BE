package com.kainos.jobnight.repo;


import com.kainos.jobnight.entity.Competency;
import com.kainos.jobnight.entity.JobFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobFamilyRepository extends JpaRepository<JobFamily, Long> {
	List<JobFamily> findAll();
	JobFamily getById(long id);
}
