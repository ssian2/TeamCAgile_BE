package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.Capability;
import com.kainos.jobnight.projections.CapabilityAndJobFamilies;
import com.kainos.jobnight.projections.CapabilityNameAndID;
import com.kainos.jobnight.projections.CapabilityWithJobRoleInfo;
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

    @GetMapping("getCapability/{name}")
    public List<CapabilityAndJobFamilies> getCapability(@PathVariable("name") String name){
        if(CapabilityRepository.getCapabilityFamiliesByName(name).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Capability does not exist");
        }else{
            return CapabilityRepository.getCapabilityFamiliesByName(name);
        }
    }

    @GetMapping("/{name}")
    public List<CapabilityWithJobRoleInfo> getAllCapabilityInfo(@PathVariable("name") String name){
        if(CapabilityRepository.getCapabilityByName(name).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Capability does not exist");
        }else{
            return CapabilityRepository.getCapabilityByName(name);
        }
    }

    @GetMapping("/getCapabilityWithFamilies")
    public List<CapabilityAndJobFamilies> getCapabilityWithFamilies(){
        return CapabilityRepository.listCapabilitiesAndJobFamilies();
    }

}
