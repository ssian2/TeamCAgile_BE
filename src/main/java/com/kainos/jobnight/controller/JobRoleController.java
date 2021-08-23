package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.repo.JobRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
