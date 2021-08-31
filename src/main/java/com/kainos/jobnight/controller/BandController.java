package com.kainos.jobnight.controller;


import com.kainos.jobnight.repo.BandRepository;
import com.kainos.jobnight.projections.BandAndTrainings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.projections.band.BandNames;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bands")
public class BandController {
	@Autowired
	private BandRepository bandRepo;
  
      @GetMapping("/all")
    public List<Band> getAllBands() {
      return bandRepo.findAll();
    }

    @GetMapping("/order")
    public List<BandNames> getBandNamesSorted() {
        return bandRepo.findAllOrderByBandLevel();
    }

    @GetMapping("/training")
    public  List<BandAndTrainings> getTrainingByBand(){
        return bandRepo.getBandsAndTrainigs();
    }

    @GetMapping("/training/{id}")
    public  BandAndTrainings getTrainingByBandByID(@PathVariable("id") Short ID){
        if (bandRepo.findById(ID).isPresent()){
            return bandRepo.getBandsAndTrainingsByBandID(ID);
        }else{
        return null;
        }
    }
    
}

