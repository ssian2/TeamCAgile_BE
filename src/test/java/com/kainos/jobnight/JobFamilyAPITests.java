package com.kainos.jobnight;

import com.kainos.jobnight.repo.JobFamilyRepository;
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

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobnightApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobFamilyAPITests {

    @Autowired
    private JobFamilyRepository jobFamilyRepository;

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private final HttpHeaders headers = new HttpHeaders();

    @Test
    void whenGetRequestIssuedToApiJobFamilyAll_thenReturnCompleteJobFamilyDataSet() {
        final String baseUrl = "http://localhost:8080/api/job-family/all";

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/job-family/all"), HttpMethod.GET, entity, String.class);

        String expected = """
                [{"name":"Engineering Strategy and Planning","capability_name":"Engineering","id":1},{"name":"Engineering","capability_name":"Engineering","id":2},{"name":"Architecture","capability_name":"Artificial Intelligence","id":3}] """;

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
