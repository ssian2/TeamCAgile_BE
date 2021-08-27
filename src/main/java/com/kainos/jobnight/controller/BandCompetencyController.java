package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.entity.Competency;
import com.kainos.jobnight.entity.CompetencyType;
import com.kainos.jobnight.repo.BandRepository;
import com.kainos.jobnight.repo.CompetencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/band-competency")
public class BandCompetencyController {
	@Autowired
	private BandRepository bandRepo;

	@Autowired
	private CompetencyRepository competencyRepo;

	//Return list of competencies sorted by band, then by competency id
	@GetMapping("/all")
	public Map<Band, Map<CompetencyType, String>> getAllCompetencies() {

		Map<Band, Map<CompetencyType, String>> map  = new HashMap<Band, Map<CompetencyType, String>>();

		List<Competency> competencies = competencyRepo.findAll();

		for (Competency c : competencies) {
			if (!map.containsKey(c.getBand())) {
				map.put(c.getBand(), new HashMap<CompetencyType, String>());
			}

			if (!map.get(c.getBand()).containsKey(c.getCompetency())){
				map.get(c.getBand()).put(c.getCompetency(), c.getDescription());
			}
		}

		return map;
		/*
		competencies.sort(new Comparator<Competency>() {
			@Override
			public int compare(Competency o1, Competency o2) {
				int bandCmp = Short.compare(o1.getBand().getId(), o2.getBand().getId());
				if (bandCmp == 0)
				{
					return Byte.compare(o1.getCompetency().getId(), o2.getCompetency().getId());
				}
				else return bandCmp;
			}
		});

		return competencies;*/
	}
}
