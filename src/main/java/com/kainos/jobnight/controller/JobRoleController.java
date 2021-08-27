package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.entity.Responsibility;
import com.kainos.jobnight.helper_classes.JobRoleNamesOnly;
import com.kainos.jobnight.helper_classes.RoleResponsibility;
import com.kainos.jobnight.repo.JobRoleRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
	public JobRole viewJobSpecById(@PathVariable("id") Short id) {
		var specId = repo.findById(id);

		if (specId.isPresent()) {
			return specId.get();
		}
		return null;
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

	@GetMapping("/test")
	public List<JobRoleNamesOnly> test(){
		return repo.queryTry();
	}
}