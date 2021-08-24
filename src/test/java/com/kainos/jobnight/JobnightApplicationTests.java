package com.kainos.jobnight;

import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.repo.JobRoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JobnightApplicationTests {

	@Autowired
	private JobRoleRepository jobRoleRepository;
/*
	@Test
	void contextLoads() {
	}*/
/*
	@Test
	void whenGetRequestIssuedToApiJobRoleAll_thenReturnCompleteJobRoleDataSet() {
		final String baseUrl = "http://localhost:8080/api/job-role/all";

		// Expect two records in result set
		assertEquals(2, all.size());

		// Check for expected data in results set
	}*/
}
