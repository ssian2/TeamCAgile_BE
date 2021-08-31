package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.projections.band.BandNames;
import com.kainos.jobnight.repo.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/band")
public class BandController {

    @Autowired
    BandRepository repository;

    @GetMapping("/all")
	public List<Band> getAllBands() {
		return repository.findAll();
	}

    @GetMapping("/order")
    public List<BandNames> getBandNamesSorted() {
        return repository.findAllOrderByBandLevel();
    }
}
