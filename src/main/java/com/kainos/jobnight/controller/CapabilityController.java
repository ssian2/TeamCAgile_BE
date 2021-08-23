package com.kainos.jobnight.controller;

import com.kainos.jobnight.model.CapabilityModel;
import com.kainos.jobnight.repository.CapabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CapabilityController {

    @Autowired
    CapabilityRepository CapabilityRepository;

    @GetMapping("/capabilities")
    public List<CapabilityModel> getAllCapabilities() {
        return CapabilityRepository.findAll();
    }
}
