package com.kainos.jobnight.repo;

import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.projections.JobRole.JobRoleWithBandandFamily;
import com.kainos.jobnight.projections.JobRole.JobRoleWithBrandFamilyUrlAndSpec;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRoleRepository extends CrudRepository<JobRole, Short>
{
	List<JobRole> findAll();
    
	Optional<JobRole> findById(Short id);

	@Query("Select j from JobRole j JOIN j.responsibilities ")
	public List<JobRole> testQuery();


	@Query("Select j from JobRole j")
	List<JobRoleWithBandandFamily> jobRoleWithBandAndFamily();

	@Query("Select role from JobRole role where role.id = ?1")
	List<JobRoleWithBrandFamilyUrlAndSpec> getJobRoleDetailsById(short id);

	@Query("Select role from JobRole role JOIN role.jobFamily JOIN role.jobFamily.capability where role.jobFamily.capability.name = ?1")
	List<JobRoleWithBandandFamily> getJobRoleDetailsByCapabilityName(String name);

	<S extends JobRole> S save(S entity);
	@Query("SELECT j from JobRole j JOIN j.responsibilities where j.id = ?1")
    JobRole getRoleWithRespById(Short id);
}
