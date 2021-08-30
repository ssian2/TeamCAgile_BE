package com.kainos.jobnight.controller;

import com.kainos.jobnight.projections.jobFamily.JobFamilyNames;
import com.kainos.jobnight.repo.JobFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/job-family")
public class JobFamilyController {

    @Autowired
    JobFamilyRepository jobFamilyRepository;

    @GetMapping("/byCapability/{name}")
    List<JobFamilyNames> findAllByCapability(@PathVariable("name") String name) {
        if (jobFamilyRepository.findAllByCapability(name).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Capability does not exist");
        } else {
            return jobFamilyRepository.findAllByCapability(name);
        }
    }

}
