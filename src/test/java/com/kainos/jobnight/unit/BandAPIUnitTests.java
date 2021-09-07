package com.kainos.jobnight.unit;

import com.kainos.jobnight.JobnightApplication;
import com.kainos.jobnight.controller.BandController;
import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.projections.band.BandNames;
import com.kainos.jobnight.repo.BandRepository;
import com.kainos.jobnight.repo.CapabilityRepository;
import com.kainos.jobnight.repo.JobFamilyRepository;
import com.kainos.jobnight.repo.JobRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = JobnightApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BandAPIUnitTests {
	@LocalServerPort
	private int port;

	@Mock
	private BandRepository bandRepo;

	@Mock
	private JobRoleRepository jobRoleRepo;

	@Mock
	private JobFamilyRepository jobFamilyRepo;

	@Mock
	private CapabilityRepository capRepo;

	@InjectMocks
	private BandController bandController;

	private ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

	@BeforeEach
	void init() {
		bandController = new BandController(jobRoleRepo, bandRepo, jobFamilyRepo);
	}

	@Test
	void whenBandControllerGetAllBandsInvoked_thenInvokeBandRepositoryFindAll() {
		when(bandRepo.findAll()).thenReturn(new ArrayList<>());

		List<Band> jobRoles = bandController.getAllBands();

		verify(bandRepo, times(1)).findAll();
	}


	@Test // US011 - Test BandNames Projection
	void whenBandNamesProjectionInvoked_thenReturnTheNameOfBand() {

		Map<String, Object> backingMap = new HashMap<>();
		backingMap.put("name", "Manager");

		var bandNamesProjection = factory.createProjection(BandNames.class, backingMap);

		assertThat(bandNamesProjection.getName()).isEqualTo("Manager");
	}

	// US011 - Test BandNames Projection
	@Test
	void whenBandControllerGetBandNamesSortedInvoked_thenInvokeBandRepositoryFindAllOrderByBandLevelOnce() {
		when(bandRepo.findAllOrderByBandLevel()).thenReturn(new ArrayList<>());

		List<BandNames> bandNames = bandController.getBandNamesSorted();

		verify(bandRepo, times(1)).findAllOrderByBandLevel();
	}


}