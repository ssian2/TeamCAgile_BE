package com.kainos.jobnight.unit;

import com.kainos.jobnight.JobnightApplication;
import com.kainos.jobnight.controller.JobRoleController;
import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.entity.JobFamily;
import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.entity.Responsibility;
import com.kainos.jobnight.helper_classes.RoleResponsibility;
import com.kainos.jobnight.helper_classes.Validator;
import com.kainos.jobnight.repo.BandRepository;
import com.kainos.jobnight.repo.CapabilityRepository;
import com.kainos.jobnight.repo.JobFamilyRepository;
import com.kainos.jobnight.repo.JobRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = JobnightApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobRoleTest {

    @LocalServerPort
    private int port;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private JobRoleRepository jobRoleRepo;

    @Mock
    private BandRepository bandRepo;

    @Mock
    private JobFamilyRepository jobFamilyRepo;

    @Mock
    private CapabilityRepository capRepo;

    @InjectMocks
    private JobRoleController jobRoleController;

    @BeforeEach
    void init() {
        jobRoleController = new JobRoleController(jobRoleRepo, bandRepo, jobFamilyRepo);
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
            new JobRole((short) 1, "name1", "spec1", "url1"),
            new JobRole((short) 2, "name2", "spec2", "url2"),
            new JobRole((short) 3, "name3", "spec3", "url3")
        );

        when(jobRoleRepo.findAll()).thenReturn(expected);

        List<JobRole> actual = jobRoleController.getAllJobRoles();

        assertEquals(expected, actual);
    }
    //TODO: Behavior has changed significantly since these tests were written, change them.
/*
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
    }*/

    @Test // US006
	void whenJobRoleControllerGetResponsibilitiesPerRoleInvoked_thenInvokeJobRoleRepositoryTestOnce() {
    	when(jobRoleRepo.jobRoleResponsibilities()).thenReturn(List.of());

    	jobRoleController.getResponsibilitiesPerRole();

    	verify(jobRoleRepo, times(1)).jobRoleResponsibilities();
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
        when(jobRoleRepo.jobRoleResponsibilities()).thenReturn(input);

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

		assertNotNull(validator);

		Map<String, String> sources = validator.getSources();

		assertEquals(sources.get("name"), "Value must exist.");
		assertEquals(sources.get("summary"), "Value must exist.");
		assertEquals(sources.get("band"), "Value must exist.");
		assertEquals(sources.get("job_family"), "Value must exist.");
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	///////////////////////////////////
	/////////// US012 NAME ////////////
	///////////////////////////////////

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
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test // US012
	void whenJobRoleControllerAddJobRoleInvokedAndNameIsTooLong_thenProvideValueTooLongMessageOnName() {
		when(bandRepo.findAll()).thenReturn(List.of());
		when(jobFamilyRepo.findAll()).thenReturn(List.of());

		ResponseEntity<Validator> response = jobRoleController.addJobRole("""
  {"name":"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"}
  """);
		Validator validator = response.getBody();

		assertNotNull(validator);
		assertEquals(validator.getSources().get("name"), "Value is too long.");
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	///////////////////////////////////
	////////// US012 SUMMARY //////////
	///////////////////////////////////

	@Test // US012
	void whenJobRoleControllerPostJobRoleInvokedAndSummaryIsTooLong_thenProvideValueTooLongMessageOnSummary() {
		String request = """
  {"summary": "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"}
  """;
		when(bandRepo.findAll()).thenReturn(List.of());
		when(jobFamilyRepo.findAll()).thenReturn(List.of());

		ResponseEntity<Validator> response = jobRoleController.addJobRole(request);
		Validator validator = response.getBody();

		assertNotNull(response.getBody());
		assertEquals("Value is too long.",validator.getSources().get("summary"));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test // US012
	void whenJobRoleControllerPostJobRoleInvokedWithWrongBand_thenExpectError() {
		String request = "{\"name\": \"name\",\"summary\": \"test summary\", \"band\": \"3\",\"job_family\":\"1\"}";

		List<Band> bands_returned  = List.of(
			new Band("test band", (short)1),
			new Band("test", (short)2));
		List<JobFamily> families_returned  = List.of(
			new JobFamily((short)1,"test family 1",null, null),
			new JobFamily((short)2,"test family 2",null, null));
		when(bandRepo.findAll()).thenReturn(bands_returned);
		when(jobFamilyRepo.findAll()).thenReturn(families_returned);

		ResponseEntity<Validator> response = jobRoleController.addJobRole(request);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test // US012
	void whenJobRoleControllerPostJobRoleInvokedWithWrongJobFamily_thenExpectError() {
		String request = "{\"name\": \"name\",\"summary\": \"test summary\", \"band\": \"1\",\"job_family\":\"3\"}";

		List<Band> bands_returned  = List.of(
			new Band("test band 1", (short)1),
			new Band("test band 2", (short)2));
		List<JobFamily> families_returned  = List.of(
			new JobFamily((short)1,"test family 1",null, null),
			new JobFamily((short)2,"test family 2",null, null));
		when(bandRepo.findAll()).thenReturn(bands_returned);
		when(jobFamilyRepo.findAll()).thenReturn(families_returned);

		ResponseEntity<Validator> response = jobRoleController.addJobRole(request);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test // US012
	void whenJobRoleControllerPostJobRoleInvokedWithNoSummary_thenExpectSummaryMustNotBeNullErrorMessage() {
		String request = "{\"name\": \"name\",\"summary\": \"\", \"band\": \"1\",\"job_family\":\"1\"}";

		List<Band> bands_returned  = List.of(
			new Band("test band 1", (short)1),
			new Band("test band 2", (short)2));
		List<JobFamily> families_returned  = List.of(
			new JobFamily((short)1,"test family 1",null, null),
			new JobFamily((short)2,"test family 2",null, null));
		when(bandRepo.findAll()).thenReturn(bands_returned);
		when(jobFamilyRepo.findAll()).thenReturn(families_returned);

		ResponseEntity<Validator> response = jobRoleController.addJobRole(request);

		assertEquals("Value must not be empty.",response.getBody().getSources().get("summary"));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test // US012
	void whenJobRoleControllerPostJobRoleInvokedWithTooLongName_thenExpectNameTooLongErrorMessage() {
		String request = "{\"name\": \"namenamenamenamenamenamenamenamenamenamenamenamename\",\"summary\": \"\", \"band\": \"1\",\"job_family\":\"1\"}";

		List<Band> bands_returned  = List.of(
			new Band("test band 1", (short)1),
			new Band("test band 2", (short)2));
		List<JobFamily> families_returned  = List.of(
			new JobFamily((short)1,"test family 1",null, null),
			new JobFamily((short)2,"test family 2",null, null));
		when(bandRepo.findAll()).thenReturn(bands_returned);
		when(jobFamilyRepo.findAll()).thenReturn(families_returned);

		ResponseEntity<Validator> response = jobRoleController.addJobRole(request);

		assertEquals("Value is too long.",response.getBody().getSources().get("name"));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test // US012
	void whenJobRoleControllerPostJobRoleInvokedWithValidBody_thenInvokeJobRoleRepositorySave() {

		List<Band> bands_returned  = List.of(
			new Band("test band 1", (short)1),
			new Band("test band 2", (short)2));

		List<JobFamily> families_returned  = List.of(
			new JobFamily((short)1,"test family 1",null, null),
			new JobFamily((short)2,"test family 2",null, null));

		when(bandRepo.findAll()).thenReturn(bands_returned);
		when(jobFamilyRepo.findAll()).thenReturn(families_returned);
		when(jobRoleRepo.save(any())).thenReturn(null);

		ResponseEntity<Validator> response = jobRoleController.addJobRole("""
  {
  "name": "name",
  "summary": "test summary",
  "band": "2",
  "job_family": "1"
  }""");

		verify(jobRoleRepo, times(1)).save(any());
		assertEquals(HttpStatus.OK, response.getStatusCode());
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

	// US020
	@Test
	void whenJobRoleYouWantToDeleteIsPresent_thenExpectSuccesMessage(){
		Short ID = (short) 1;
		when(jobRoleRepo.findById(ID)).thenReturn(Optional.of(new JobRole(ID, "name", "spec", "url", (Band) null, null, null)));

		String result= jobRoleController.deleteJobRoleByObject(ID);
		assertEquals("Deleted", result);
	}
	@Test
	void whenJobRoleYouWantToDeleteIsNotPresent_thenExpectFailureMessage(){
		Short ID = (short) 1;
		when(jobRoleRepo.findById(ID)).thenReturn(Optional.empty());
		String result= jobRoleController.deleteJobRoleByObject(ID);
		assertEquals("Not deleted", result);
	}
}