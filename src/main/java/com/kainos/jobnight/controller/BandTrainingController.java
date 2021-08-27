package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.entity.Competency;
import com.kainos.jobnight.repo.BandRepository;
import com.kainos.jobnight.repo.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/band-training")
public class BandTrainingController {

    @Autowired
    private BandRepository bandRepo;

    @Autowired
    private TrainingRepository trainingRepo;
}
