package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.entity.Capability;
import com.kainos.jobnight.repo.BandRepository;
import com.kainos.jobnight.repo.CapabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/band")
public class BandController {
	@Autowired
	BandRepository bandRepo;

	@GetMapping({"/", ""})
	public List<Band> getAllBands() {
		return bandRepo.findAll();
	}

	@GetMapping("/{id}")
	public Band getBand(@PathVariable("id") short ID){
		if(bandRepo.findById(ID).isPresent()){
			return bandRepo.findById(ID).get();
		}else{
			return null;
		}
	}
}
