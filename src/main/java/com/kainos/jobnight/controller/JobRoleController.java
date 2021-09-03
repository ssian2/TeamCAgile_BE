package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.entity.Responsibility;
import com.kainos.jobnight.helper_classes.RoleResponsibility;
import com.kainos.jobnight.projections.JobRole.JobRoleWithBandandFamily;
import com.kainos.jobnight.projections.JobRole.JobRoleWithBrandFamilyUrlAndSpec;
import com.kainos.jobnight.repo.JobRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/job-role")
public class JobRoleController {
	@Autowired
	private JobRoleRepository repo;
	

	@GetMapping("/all")
	public List<JobRole> getAllJobRoles() {
		return repo.findAll();
	}
	
	@GetMapping("/view-job-spec/{id}")
	public List<JobRoleWithBrandFamilyUrlAndSpec> viewJobSpecById(@PathVariable("id") Short id) {
		if (repo.findById(id).isPresent()) {
			return repo.getJobRoleDetailsById(id);
		}
		throw new ResponseStatusException(NOT_FOUND, "Job role does not exist");
	}

	@GetMapping("/view-band-level")
	public List<JobRole> viewBandLevel() {
		return repo.findAll();
	}

	@GetMapping("/view-responsibilities-per-role")
	public List<RoleResponsibility>  getRespsPerRole()
	{
		List<JobRole> roles =  repo.testQuery();
		List<RoleResponsibility> JoinedData= new ArrayList<RoleResponsibility>();
		for(JobRole j: roles){
				Set<Responsibility> resps = j.getResponsibilities();
				RoleResponsibility data = new RoleResponsibility(j.getName());
				for(Responsibility r: resps){
					
					data.AddResponsibility(r.getName());
				}
				if (!data.resps.isEmpty()){
					JoinedData.add(data);
				}
			}
		return JoinedData;
	}


	@GetMapping("/jobRolesWithBandAndFamily")
	public List<JobRoleWithBandandFamily> jobRoleBandAndFamily(){
		return repo.jobRoleWithBandAndFamily();
	}


	@GetMapping("/byCapability/{name}")
	public List<JobRoleWithBandandFamily> jobRoleBandAndFamilyByCapability(@PathVariable("name") String name) {
		if (repo.getJobRoleDetailsByCapabilityName(name).isEmpty()) {
			throw new ResponseStatusException(NOT_FOUND, "No Job roles in this capability");
		} else {
			return repo.getJobRoleDetailsByCapabilityName(name);

		}
	}

	@GetMapping("/view-responsibilities-per-role/{id}")
	public RoleResponsibility getRespsPerRoleByID(@PathVariable("id") Short ID)
	{
		if(repo.findById(ID).isPresent()){
			var result = repo.getRoleWithRespById(ID);
			var resps = result.getResponsibilities();
			RoleResponsibility data = new RoleResponsibility(result.getName());
			for (Responsibility r: resps){
				data.AddResponsibility(r.getName());
			}
			return data;
		}else{
			return null;

		}
	}

	@DeleteMapping(value = "/delete_jobrole")
	public String deleteJobRoleByObject(@RequestBody String id)
	{
		repo.deleteById(Short.parseShort(id));
		return "Deleted";
	}
}