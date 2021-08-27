package com.kainos.jobnight.repo;


import com.kainos.jobnight.entity.Capability;
import com.kainos.jobnight.projections.CapabilityAndJobFamilies;
import com.kainos.jobnight.projections.CapabilityNameAndID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapabilityRepository extends CrudRepository<Capability, Short> {

    List<Capability> findAll();

    @Query("SELECT c from Capability c join c.jobFamilies")
    List<CapabilityAndJobFamilies> listCapabilitiesAndJobFamilies();

    @Query("SELECT c from Capability c where c.ID = ?1")
    List<CapabilityNameAndID> getCapabilityByID(Short id);


}
