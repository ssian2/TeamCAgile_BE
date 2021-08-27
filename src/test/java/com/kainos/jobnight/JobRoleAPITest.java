package com.kainos.jobnight;

import com.kainos.jobnight.repo.JobRoleRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.kainos.jobnight.Util.createURLWithPort;
import static com.kainos.jobnight.Util.loadResourceAsString;
import static org.junit.jupiter.api.Assertions.fail;

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

    @Test
    void whenGetRequestToViewResponsibilityForRole_thenExpectCorrectResults(){
        

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        System.out.println("\n\n\n\nHEEEREEEE\n\n\n\n");
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/job-role/view-responsibilities-per-role"),
                HttpMethod.GET, entity, String.class);
        
        String expected = """
        [{"role_name":"Job Role 1","resp_name":"test_responsibility_name"},{"role_name":"Job Role 2","resp_name":"another_test_resp"}]""";
        
        System.out.printf("\n\n%s\n\n", response.getBody());

        try {
            JSONAssert.assertEquals(expected, response.getBody(), false);
        } catch (JSONException e) {
            fail("Invalid JSON object");
        }
        }






    private String createURLWithPort(String url) {
        return "http://localhost:" + port + url;

    }
}