package com.SafetyNet.alerts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.SafetyNet.alerts.service.CommunityEmailService;

public class CommunityEmailControllerTest {

	@Mock
	private CommunityEmailService communityEmailService;
	
	@InjectMocks
	private CommunityEmailController communityEmailController;
	private MockMvc mockMvc; 
	
	@BeforeEach
	void setup() { 		
		MockitoAnnotations.openMocks(this);  		
		this.mockMvc = MockMvcBuilders.standaloneSetup(communityEmailController)
			.build();
	}

	@Test
	public void shouldListOfMailsTest() throws Exception {
		when(communityEmailService.listOfMails("Culver"))
			.thenReturn(Arrays.asList("jaboyd@email.com", "bstel@email.com", "drk@email.com"));
		
		
		mockMvc.perform( 							
			MockMvcRequestBuilders.get("/communityEmail?city=Culver")	
			.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
				.andExpect(jsonPath("$[0]").value("jaboyd@email.com"))
				.andExpect(jsonPath("$[1]").value("bstel@email.com"))
				.andExpect(jsonPath("$[2]").value("drk@email.com"));
	}
}
