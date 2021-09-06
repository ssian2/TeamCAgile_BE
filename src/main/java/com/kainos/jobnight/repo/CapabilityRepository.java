package com.kainos.jobnight.repo;


import com.kainos.jobnight.entity.Capability;
import com.kainos.jobnight.entity.Competency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapabilityRepository extends JpaRepository<Capability, Short> {
	List<Capability> findAll();
}
