package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.JobFamily;
import com.kainos.jobnight.repo.JobFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/job-family")
public class JobFamilyController {

    @Autowired
    JobFamilyRepository jobFamilyRepository;

    @GetMapping("/all")
    public List<JobFamily> getAllJobFamilies() {
        return jobFamilyRepository.findAll();
    }



}
