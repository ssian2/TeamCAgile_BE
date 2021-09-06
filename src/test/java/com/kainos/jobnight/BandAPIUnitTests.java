package com.kainos.jobnight;

import java.util.Arrays;

import com.kainos.jobnight.repo.BandRepository;
import com.kainos.jobnight.controller.BandController;
import com.kainos.jobnight.entity.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@WebMvcTest(value = BandController.class)

public class BandAPIUnitTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BandRepository bandRepo;

	@Test
	public void whenRootGetCalledExpectAllTheBands() throws Exception {

		Mockito.when(
				bandRepo.findAll()).thenReturn(Arrays.asList(new Band("Test Band 1",Short.parseShort("1")),new Band("Test Band 2",Short.parseShort("2"))));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"http://localhost:8080/api/bands/all").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"id\":1,\"name\":\"Test Band 1\",\"trainings\":null,\"band_level\":0},{\"id\":2,\"name\":\"Test Band 2\",\"trainings\":null,\"band_level\":0}]";
		assertEquals(expected, result.getResponse().getContentAsString());
		
    }
}