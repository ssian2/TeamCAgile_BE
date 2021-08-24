package com.kainos.jobnight.controller;

import com.kainos.jobnight.model.Capability;
import com.kainos.jobnight.repository.CapabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/capability")
public class CapabilityController {

    @Autowired
    CapabilityRepository CapabilityRepository;

    @GetMapping("/allCapabilities")
    public List<Capability> getAllCapabilities() {
        return CapabilityRepository.findAll();
    }
}
