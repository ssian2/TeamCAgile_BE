package com.kainos.jobnight;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.repo.JobRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest
class JobnightApplicationTests {

	@Autowired
	private JobRoleRepository jobRoleRepository;

	
	@Test
	void whenCallingViewBandLevelShouldReturnListofEmployeeRoleWithProperFirst(){
		
		List<JobRole> Result = jobRoleRepository.viewBandLevel();
		JobRole firstJobRole = Result.get(0);
		assertEquals(1,firstJobRole.getId());
		assertEquals("Job Role 1",firstJobRole.getName());
		assertEquals("test_band_name",firstJobRole.getBand_name().getName() );
	}
	

}
