package com.kainos.jobnight.repo;


import com.kainos.jobnight.entity.JobFamily;
import com.kainos.jobnight.projections.jobFamily.JobFamilyNames;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobFamilyRepository extends CrudRepository<JobFamily, Short> {

    @Query("Select family from JobFamily family where family.capability.name = ?1")
    List<JobFamilyNames> findAllByCapability(String name);


}
