package com.kainos.jobnight.repo;


import com.kainos.jobnight.entity.JobFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobFamilyRepository extends JpaRepository<JobFamily, Long> {

}
