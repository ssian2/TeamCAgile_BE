package com.kainos.jobnight.controller;

import com.kainos.jobnight.projections.capability.CapabilityAndJobFamilies;
import com.kainos.jobnight.repo.CapabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/capability")
public class CapabilityController {

    @Autowired
    CapabilityRepository CapabilityRepository;

    @GetMapping("getCapability/{name}")
    public List<CapabilityAndJobFamilies> getCapability(@PathVariable("name") String name){
        if(CapabilityRepository.getCapabilityFamiliesByName(name).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Capability does not exist");
        }else{
            return CapabilityRepository.getCapabilityFamiliesByName(name);
        }
    }

}
