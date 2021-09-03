package com.kainos.jobnight.unit;

import com.kainos.jobnight.JobnightApplication;
import com.kainos.jobnight.controller.JobRoleController;
import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.entity.Responsibility;
import com.kainos.jobnight.helper_classes.RoleResponsibility;
import com.kainos.jobnight.helper_classes.Validator;
import com.kainos.jobnight.repo.BandRepository;
import com.kainos.jobnight.repo.JobFamilyRepository;
import com.kainos.jobnight.repo.JobRoleRepository;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.kainos.jobnight.Util.createURLWithPort;
import static com.kainos.jobnight.Util.loadResourceAsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = JobnightApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobRoleTest {

    @LocalServerPort
    private int port;

    @Mock
    private JobRoleRepository jobRoleRepo;

    @Mock
    private BandRepository bandRepo;

    @Mock
    private JobFamilyRepository jobFamilyRepo;

    @InjectMocks
    private JobRoleController jobRoleController;

    @BeforeEach
    void init() {
        jobRoleController = new JobRoleController(
            jobRoleRepo,
            bandRepo,
            jobFamilyRepo);
    }

    @Test // US001
    void whenJobRoleControllerGetAllJobRolesInvoked_thenInvokeJobRoleRepositoryFindAllOnce() {
        when(jobRoleRepo.findAll()).thenReturn(new ArrayList<>());

        List<JobRole> jobRoles = jobRoleController.getAllJobRoles();

        verify(jobRoleRepo, times(1)).findAll();
    }

    @Test // US001
    void whenJobRoleControllerGetAllJobRolesInvoked_thenReturnListReturnedByJobRoleRepositoryFindAll() {
        List<JobRole> expected = List.of(
            new JobRole((short) 1, "name1", "spec1", "url1", null, null, null),
            new JobRole((short) 2, "name2", "spec2", "url2", null, null, null),
            new JobRole((short) 3, "name3", "spec3", "url3", null, null, null)
        );

        when(jobRoleRepo.findAll()).thenReturn(expected);

        List<JobRole> actual = jobRoleController.getAllJobRoles();

        assertEquals(expected, actual);
    }

    @Test // US001
    void whenJobRoleControllerGetJobSpecByIdInvoked_thenInvokeJobRoleRepositoryFindByIdOnce() {
        when(jobRoleRepo.findById((short) 1)).thenReturn(Optional.empty());

        jobRoleController.viewJobSpecById((short) 1);

        verify(jobRoleRepo, times(1)).findById((short) 1);
    }

    @Test // US001
    void whenJobRoleControllerGetJobSpecByIdInvokedAndJobRoleRepositoryFindByIdReturnsEmptyOptional_thenReturnNull() {
        when(jobRoleRepo.findById((short) 1)).thenReturn(Optional.empty());

        JobRole jobRole = jobRoleController.viewJobSpecById((short) 1);

        assertNull(jobRole);
    }

    @Test // US001
    void whenJobRoleControllerGetJobSpecByIdInvokedAndJobRoleRepositoryReturnsPresentOptional_thenReturnOptionalValue() {
        JobRole expected = new JobRole((short) 1, "name", "spec", "url", null, null, null);

        when(jobRoleRepo.findById((short) 1)).thenReturn(Optional.of(expected));

        JobRole actual = jobRoleController.viewJobSpecById((short) 1);

        assertEquals(expected, actual);
    }

    @Test // US006
	void whenJobRoleControllerGetResponsibilitiesPerRoleInvoked_thenInvokeJobRoleRepositoryTestQueryOnce() {
    	when(jobRoleRepo.testQuery()).thenReturn(List.of());

    	jobRoleController.getResponsibilitiesPerRole();

    	verify(jobRoleRepo, times(1)).testQuery();
	}

    @Test // US006
    void whenJobRoleControllerGetResponsibilitiesPerRoleInvoked_thenReturnListOfRoleResponsibilities() {
        List<JobRole> input = List.of(
        	new JobRole(
        		(short) 1,"name1","","",null,
                Set.of(
                    new Responsibility((short) 1,"resp 1",null),
                    new Responsibility((short) 2,"resp 2",null),
                    new Responsibility((short) 3,"resp 3",null)
				),null
            ),
            new JobRole(
                (short) 2,"name2","","",null,
                Set.of(
                    new Responsibility((short) 4,"resp 4",null),
                    new Responsibility((short) 5,"resp 5",null)
				),null
            ),
			new JobRole(
				(short) 3,"name3","","",null,
				Set.of(
					new Responsibility((short) 6,"resp 6",null),
					new Responsibility((short) 7,"resp 7",null),
					new Responsibility((short) 8,"resp 8",null),
					new Responsibility((short) 9,"resp 9",null)
				),null
			),
			// This should be excluded in the results set
			new JobRole(
				(short) 4,"name3","","",null,
				Set.of(),null
			)
        );

        List<RoleResponsibility> expected = List.of(
        	new RoleResponsibility(
        		"name1",
				List.of(
					"resp 1",
					"resp 2",
					"resp 3"
				)
			),
			new RoleResponsibility(
				"name2",
				List.of(
					"resp 4",
					"resp 5"
				)
			),
			new RoleResponsibility(
				"name3",
				List.of(
					"resp 6",
					"resp 7",
					"resp 8",
					"resp 9"
				)
			)
		);
        when(jobRoleRepo.testQuery()).thenReturn(input);

        List<RoleResponsibility> actual = jobRoleController.getResponsibilitiesPerRole();

        assertEquals(expected, actual);
    }

    @Test // US012
	void whenJobRoleControllerAddJobRoleInvokedAndBodyIsNull_thenReturnBadRequest() {
    	when(bandRepo.findAll()).thenReturn(List.of());
    	when(jobFamilyRepo.findAll()).thenReturn(List.of());

    	ResponseEntity<Validator> response = jobRoleController.addJobRole(null);

    	assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test // US012
	void whenJobRoleControllerAddJobRoleInvokedAndBodyIsNull_thenJobRoleRepositorySaveNotInvoked() {
		when(bandRepo.findAll()).thenReturn(List.of());
		when(jobFamilyRepo.findAll()).thenReturn(List.of());

		jobRoleController.addJobRole(null);

		verify(jobRoleRepo, times(0)).save(any());
	}

	@Test // US012
	void whenJobRoleControllerAddJobRoleInvokedAndBodyIsNull_thenProvideValueMustExistMessageOnAllFields() {
		when(bandRepo.findAll()).thenReturn(List.of());
		when(jobFamilyRepo.findAll()).thenReturn(List.of());

		ResponseEntity<Validator> response = jobRoleController.addJobRole(null);
		Validator validator = response.getBody();

		Map<String, String> sources = validator.getSources();

		assertEquals(sources.get("name"), "Value must exist.");
		assertEquals(sources.get("summary"), "Value must exist.");
		assertEquals(sources.get("band"), "Value must exist.");
		assertEquals(sources.get("job_family"), "Value must exist.");
	}

	@Test // US012
	void whenJobRoleControllerAddJobRoleInvokedAndNameIsEmpty_thenProvideValueMustNotBeEmptyMessageOnName() {
		when(bandRepo.findAll()).thenReturn(List.of());
		when(jobFamilyRepo.findAll()).thenReturn(List.of());

		ResponseEntity<Validator> response = jobRoleController.addJobRole("""
  {"name":""}
  """);
		Validator validator = response.getBody();

		Map<String, String> sources = validator.getSources();

		assertEquals(sources.get("name"), "Value must not be empty.");
	}

	@Test // US012
	void whenJobRoleControllerAddJobRoleInvokedAndNameIsTooLong_thenProvideValueTooLongMessageOnName() {
		when(bandRepo.findAll()).thenReturn(List.of());
		when(jobFamilyRepo.findAll()).thenReturn(List.of());

		ResponseEntity<Validator> response = jobRoleController.addJobRole("""
  {"name":"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"}
  """);
		Validator validator = response.getBody();

		Map<String, String> sources = validator.getSources();

		assertEquals(sources.get("name"), "Value is too long.");
	}

	@Test // US012
	void whenJobRoleControllerAddJobRoleInvokedAndSummaryIsEmpty_thenProvideValueMustNotBeEmptyOnSummary() {
		when(bandRepo.findAll()).thenReturn(List.of());
		when(jobFamilyRepo.findAll()).thenReturn(List.of());

		ResponseEntity<Validator> response = jobRoleController.addJobRole("""
  {"summary":""}
  """);
		Validator validator = response.getBody();

		Map<String, String> sources = validator.getSources();

		assertEquals(sources.get("summary"), "Value must be present.");
	}

    //US012_A
	/*
    @Test
    void whenPostRequestIssuedToApiJobRoleAdd_thenJobRoleControllerAddJobRoleInvoked() {
        //TODO: put PUT request here
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/api/job-role/add", port),
            HttpMethod.PUT, entity, String.class);

        String expected = loadResourceAsString("Test_US012_A_Expected.json");

        try {
            JSONAssert.assertEquals(expected, response.getBody(), true);
        } catch (JSONException ex) {
            fail("Invalid JSON object");
        }
    }*/
}