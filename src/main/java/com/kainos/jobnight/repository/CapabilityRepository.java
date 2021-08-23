package com.kainos.jobnight.repository;


import com.kainos.jobnight.model.CapabilityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityRepository extends JpaRepository<CapabilityModel, Long> {

}
