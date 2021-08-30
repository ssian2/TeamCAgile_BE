package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.entity.Competency;
import com.kainos.jobnight.entity.CompetencyType;
import com.kainos.jobnight.helper_classes.CompetenciesOfEachBand;
import com.kainos.jobnight.projections.BandWithCompetencies;
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
	}


	@GetMapping("/bands-with-competencies")
	List<CompetenciesOfEachBand> getBandWithCompetencies(){ 
		var  allInfo = competencyRepo.getBandWithCompetencies();
		List<CompetenciesOfEachBand> ListOfDataFrames  = new ArrayList<CompetenciesOfEachBand>();
		Set<String> bandNames = new HashSet<String>();
		for(BandWithCompetencies b: allInfo) {
			bandNames.add(b.getBandName());
		}
		for(String name: bandNames)
		{
			var dataFrame = new CompetenciesOfEachBand(name);
			ListOfDataFrames.add(dataFrame);
		}
		for(BandWithCompetencies b: allInfo) {
			for(CompetenciesOfEachBand c : ListOfDataFrames)
			{
				if(c.getName().equals(b.getBandName()))
				{
					c.addCompetencyInfo(b.getCompetencyTypeName(), b.getDescription());
				}
			}
		}
		return ListOfDataFrames;
	}
		

}
