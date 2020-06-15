package com.lawnmower.libonTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LawnMowerApiControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	void testGetMovingInformations() throws Exception {
		mvc.perform(post("/MovementsInformationsV1").contentType(MediaType.APPLICATION_JSON).content("{\r\n" + 
				"    \"payLoad\": \"5 5\\r\\n1 2 N\\r\\nGAGAGAGAA\\r\\n3 3 E\\r\\nAADAADADDA\"\r\n" + 
				"}")).andExpect(status().is2xxSuccessful());
	}

}
