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

import static com.kainos.jobnight.Util.createURLWithPort;
import static com.kainos.jobnight.Util.loadResourceAsString;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobnightApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CapabilityAPITests {
    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private final HttpHeaders headers = new HttpHeaders();

    // US010
    @Test
    void whenGetRequestIssuedToApiCapabilityLeadAll_thenReturnCompleteCapabilityLeadDataSet() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/capability/leads", port),
                HttpMethod.GET, entity, String.class);

        String expected = loadResourceAsString("Test_US010_Expected.json");

        try {
            JSONAssert.assertEquals(expected, response.getBody(), false);
        } catch (JSONException e) {
            fail("Invalid JSON object");
        }
    }

    // US011
    @Test
    void whenGetRequestIssuedToApiCapabilityAll_thenReturnCompleteCapabilityDataSet() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/capability/getCapability/Product", port),
                HttpMethod.GET, entity, String.class);

        String expected = loadResourceAsString("Test_US011_Expected.json");

        try {
            JSONAssert.assertEquals(expected, response.getBody(), false);
        } catch (JSONException e) {
            fail("Invalid JSON object");
        }
     }
}
