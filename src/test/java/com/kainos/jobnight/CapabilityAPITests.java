package com.kainos.jobnight;

import com.kainos.jobnight.repository.CapabilityRepository;
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
public class CapabilityAPITests {

    @Autowired
    private CapabilityRepository capabilityRepository;

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private final HttpHeaders headers = new HttpHeaders();

    @Test
    void whenGetRequestIssuedToApiCapabilityAll_thenReturnCompleteCapabilityDataSet() {
        final String baseUrl = "http://localhost:8080/api/capability/all";

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/capability/all"), HttpMethod.GET, entity, String.class);

        String expected = """
                    [{"name":"Engineering","jobroles":[{"id":1,"name":"Job Role 1"}],"id":1},{"name":"Artificial Intelligence","jobroles":[{"id":2,"name":"Job Role 2"}],"id":2}]""";

        System.out.println("hello" + response.getBody());

        try {
            System.out.println(response.getBody());
            JSONAssert.assertEquals(expected, response.getBody(), false);
        } catch (JSONException e) {
            fail("Invalid JSON object");
        }

    }

    private String createURLWithPort(String url) {
        return "http://localhost:" + port + url;
    }

}
