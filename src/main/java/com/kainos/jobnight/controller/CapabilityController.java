package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.Capability;
import com.kainos.jobnight.projections.CapabilityAndJobFamilies;
import com.kainos.jobnight.projections.CapabilityNameAndID;
import com.kainos.jobnight.repo.CapabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/capability")
public class CapabilityController {

    @Autowired
    CapabilityRepository CapabilityRepository;

    @GetMapping("/all")
    public List<Capability> getAllCapabilities() {
        return CapabilityRepository.findAll();
    }

    @GetMapping("get/{id}")
    public List<CapabilityNameAndID> getCapability(@PathVariable("id") short ID){
        if(CapabilityRepository.findById(ID).isPresent()){
            return CapabilityRepository.getCapabilityByID(ID);
        }else{
            return null;
        }
    }

    @GetMapping("/getCapabilityWithFamilies")
    public List<CapabilityAndJobFamilies> getCapabilityWithFamilies(){
        return CapabilityRepository.listCapabilitiesAndJobFamilies();
    }

}
