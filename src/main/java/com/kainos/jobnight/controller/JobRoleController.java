package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.repo.JobRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

	@GetMapping("/view-job-spec")
	public List<JobRole> viewJobSpec(){
		return repo.findAll();
	}


	@GetMapping("/view-band-level")
	public List<JobRole> viewBandLevel() {
		return repo.findAll();
	}
}