package com.kainos.jobnight.controller;

import com.kainos.jobnight.repo.BandRepository;
import com.kainos.jobnight.projections.BandAndTrainings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bands")
public class BandController {
	@Autowired
	private BandRepository bandRepo;

    @GetMapping("/training")
    public  List<BandAndTrainings> getTrainingByBand(){
        return bandRepo.getBandsAndTrainigs();
    }
    
}

