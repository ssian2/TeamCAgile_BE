package com.kainos.jobnight.repo;


import com.kainos.jobnight.entity.Capability;
import com.kainos.jobnight.projections.capability.CapabilityAndJobFamilies;
import com.kainos.jobnight.projections.capability.CapabilityNameAndID;
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

    @Query("SELECT c from Capability c where c.name = ?1")
    List<CapabilityAndJobFamilies> getCapabilityFamiliesByName(String name);


}
