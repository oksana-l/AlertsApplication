package com.SafetyNet.alerts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.SafetyNet.alerts.dto.PersonInfoDTO;
import com.SafetyNet.alerts.service.PersonInfoService;

public class PersonInfoControllerTest {

	@Mock
	private PersonInfoService personInfoService;
	
	@InjectMocks
	private PersonInfoController personInfoController;
	private MockMvc mockMvc; 
	

	@BeforeEach
	void setup() { 		
		MockitoAnnotations.openMocks(this);  		
		this.mockMvc = MockMvcBuilders.standaloneSetup(personInfoController)
			.build();
	}
	
	@Test
	public void shouldPersonInfoTest() throws Exception {
		when(personInfoService.personInfo("John", "Boyd"))
		.thenReturn(new PersonInfoDTO("John", "15", 38, "1", null));
		mockMvc.perform( 							
				MockMvcRequestBuilders.get("/personInfo?firstName=John&lastName=Boyd")
					.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
}
