package com.SafetyNet.alerts.controller;

import static org.mockito.Mockito.when;
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

import com.SafetyNet.alerts.dto.ChildAlertDTO;
import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.service.ChildAlertService;

public class ChildAlertControllerTest {
	
	@Mock
	private ChildAlertService dataChildAlertService;
	
	@InjectMocks
	private ChildAlertController childAlertController;
	private MockMvc mockMvc; 

	@BeforeEach
	void setup() { 		
		MockitoAnnotations.openMocks(this);  		
		this.mockMvc = MockMvcBuilders.standaloneSetup(childAlertController)
			.build();
	}
	
	@Test
	public void shouldListOfChildPerAddressTest() throws Exception {
		when(dataChildAlertService.listOfChildPerAddress("1509 Culver St"))
			.thenReturn(Arrays.asList(new ChildAlertDTO("Jacob", "Boyd", 12, 
					Arrays.asList(new PersonDTO("", "")))));
		
		mockMvc.perform( 							
				MockMvcRequestBuilders.get("/childAlert?address=1509 Culver St")
					.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].firstName").value("Jacob"))
		.andExpect(jsonPath("$[0].lastName").value("Boyd"))
		.andExpect(jsonPath("$[0].age").value(12))
		.andExpect(jsonPath("$[0].listOfAdult[0].firstName").value(""))
		.andExpect(jsonPath("$[0].listOfAdult[0].lastName").value(""));
	}
}
