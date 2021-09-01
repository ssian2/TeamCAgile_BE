package com.kainos.jobnight;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.oas.mappers.StyleEnumMapper;

import static com.kainos.jobnight.Util.loadResourceAsString;
import static org.junit.jupiter.api.Assertions.fail;
import static com.kainos.jobnight.Util.createURLWithPort;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobnightApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobRoleAPITest {
    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private final HttpHeaders headers = new HttpHeaders();

    // US001
    @Test
    void whenGetRequestIssuedToApiJobRoleAll_thenReturnCompleteJobRoleDataSet() {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/job-role/all", port),
                HttpMethod.GET, entity, String.class);
        
        String expected = loadResourceAsString("Test_US001_Expected.json");

        try {
            JSONAssert.assertEquals(expected, response.getBody(), false);
        } catch (JSONException e) {
            fail("Invalid JSON object");
        }

        // Check for expected data in results set
    }

    // US002
    @Test
    void whenGetRequestIssuedToApiJobRoleViewJobSpec1_thenReturnRequiredSpecificationDataSet() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/job-role/view-job-spec/1", port), HttpMethod.GET, entity, String.class);

        String expected = loadResourceAsString("Test_US002_Expected.json");

        try {
            JSONAssert.assertEquals(expected.toString(), response.getBody(), false);
        } catch (JSONException e) {
            fail("Invalid JSON object");
        }

    }

    //USOO3
    @Test
    void whenGetRequestIssuedToApiJobRoleViewJobRolesWithCapabilityInfo_thenReturnRequiredJobRoleInfo() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/job-role/jobRolesWithBandAndFamily", port), HttpMethod.GET, entity, String.class);

        String expected = loadResourceAsString("Test_US003_Expected.json");

        try {
            JSONAssert.assertEquals(expected.toString(), response.getBody(), false);
        } catch (JSONException e) {
            fail("Invalid JSON object");
        }

    }

    //US006
    @Test
    void whenGetRequestToViewResponsibilityForRole_thenExpectCorrectResults() {

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

    //USOO7
    @Test
    void whenGetRequestIssuedToApiJobRoleViewJobRoleByCapability_thenReturnRequiredJobRoleInfo() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/job-role/byCapability/Product", port), HttpMethod.GET, entity, String.class);

        
        String expected = loadResourceAsString("Test_US007_Expected.json");

        try {
            JSONAssert.assertEquals(expected.toString(), response.getBody(), false);
        } catch (JSONException e) {
            fail("Invalid JSON object");
        }

    }

}
