package com.kainos.jobnight.unit;

import com.kainos.jobnight.JobnightApplication;
import com.kainos.jobnight.controller.JobRoleController;
import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.entity.Responsibility;
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
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    void whenJobRoleControllerGetResponsibilitiesPerRoleInvoked_thenReturnListOfRoleResponsibilities() {
        List<JobRole> input = Arrays.asList(
        	new JobRole(
        		(short) 1,"name1","","",null,
                new HashSet<>(Arrays.asList(
                    new Responsibility((short) 1,"resp 1",null),
                    new Responsibility((short) 2,"resp 2",null),
                    new Responsibility((short) 3,"resp 3",null)
				)),null
            ),
            new JobRole(
                (short) 2,"name2","","",null,
                new HashSet<>(Arrays.asList(
                    new Responsibility((short) 4,"resp 4",null),
                    new Responsibility((short) 5,"resp 5",null)
				)),null
            ),
			new JobRole(
				(short) 3,"name3","","",null,
				new HashSet<>(Arrays.asList(
					new Responsibility((short) 6,"resp 6",null),
					new Responsibility((short) 7,"resp 7",null),
					new Responsibility((short) 8,"resp 8",null),
					new Responsibility((short) 9,"resp 9",null)
				)),null
			),
			new JobRole(
				(short) 4,"name3","","",null,
				new HashSet<>(

				),null
			)
        );
        when(jobRoleRepo.testQuery()).thenReturn(input);
    }

/*

    //US006
    @Test
    void whenGetRequestToViewResponsibilityForRole_thenExpectCorrectResults(){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/job-role/view-responsibilities-per-role", port),
                HttpMethod.GET, entity, String.class);
        
        String expected = loadResourceAsString("Test_US006_Expected.json");

        try {
            JSONAssert.assertEquals(expected, response.getBody(), false);
        } catch (JSONException e) {
            fail("Invalid JSON object");
        }
    }

    //US012_A
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