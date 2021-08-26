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
/*
	@Test
	void contextLoads() {
	}*/

	// US001
	@Test
	void whenGetRequestIssuedToApiJobRoleAll_thenReturnCompleteJobRoleDataSet() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
			createURLWithPort("/api/job-role/all"),
			HttpMethod.GET, entity, String.class);

		String expected = loadResourceAsString("Test_US001_Expected.json");

		try {
			JSONAssert.assertEquals(expected, response.getBody(), true);
		} catch (JSONException e) {
			fail("Invalid JSON object");
		}


		// Check for expected data in results set
	}

	// US005
	@Test
	void whenGetRequestIssuedToApiBandCompetencyAll_thenReturnCompleteCompetencyDataSetSortedByBand() {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
			createURLWithPort("/api/band-competency/all"),
			HttpMethod.GET, entity, String.class);

		String expected = loadResourceAsString("Test_US005_Expected.json");

		try {
			JSONAssert.assertEquals(expected, response.getBody(), true);
		} catch (JSONException e) {
			fail("Invalid JSON object");;
		}
	}

	private String createURLWithPort(String url) {
		return "http://localhost:" + port + url;
	}

	private String loadResourceAsString(String resource) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(resource);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		return br.lines().collect(Collectors.joining());
	}
}
