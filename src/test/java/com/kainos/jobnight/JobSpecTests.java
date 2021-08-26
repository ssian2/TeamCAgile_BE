package com.kainos.jobnight;

import com.kainos.jobnight.JobnightApplication;
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

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobnightApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobSpecTests {

    @Autowired
    private JobRoleRepository jobRoleRepository;

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private final HttpHeaders headers = new HttpHeaders();
/*
	@Test
	void contextLoads() {
	}*/

    @Test
    void whenGetRequestIssuedToApiSpecificationById_thenReturnRequiredSpecificationDataSet() {
        final String baseUrl = "http://localhost:8080/api/job-role/view-job-spec/1";

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/job-role/view-job-spec/1"), HttpMethod.GET, entity, String.class);

        String expected = """
                    {"id":1,"name":"Test Job Role", "specification":"Test role spec 1"}""";

        System.out.println("hello" + response.getBody());

        try {
            //System.out.println(response.getBody());
            JSONAssert.assertEquals(expected.toString(), response.getBody(), false);
        } catch (JSONException e) {
            fail("Invalid JSON object");
        }

    }

    private String createURLWithPort(String url) {
        return "http://localhost:" + port + url;
    }
}
