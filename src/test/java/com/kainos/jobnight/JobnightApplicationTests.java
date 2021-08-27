package com.kainos.jobnight;

import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.repo.JobRoleRepository;
import org.json.JSONArray;
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

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.Buffer;
import java.util.stream.Collectors;

import static com.kainos.jobnight.Util.createURLWithPort;
import static com.kainos.jobnight.Util.loadResourceAsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobnightApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobnightApplicationTests {

	@Autowired
	private JobRoleRepository jobRoleRepository;

	@LocalServerPort
	private int port;

	private final RestTemplate restTemplate = new RestTemplate();

	private final HttpHeaders headers = new HttpHeaders();

	// US005
	@Test
	void whenGetRequestIssuedToApiBandCompetencyAll_thenReturnCompleteCompetencyDataSetSortedByBand() {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
			createURLWithPort("/api/band-competency/all", port),
			HttpMethod.GET, entity, String.class);

		String expected = loadResourceAsString("Test_US005_Expected.json");

		try {
			JSONAssert.assertEquals(expected, response.getBody(), true);
		} catch (JSONException e) {
			fail("Invalid JSON object");;
		}
	}
}
