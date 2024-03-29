package com.SafetyNet.alerts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.SafetyNet.alerts.dto.FireDTO;
import com.SafetyNet.alerts.dto.FloodDTO;
import com.SafetyNet.alerts.dto.MedicalRecordsInfoDTO;
import com.SafetyNet.alerts.service.FireService;

public class FireControllerTest {

	@Mock
	private FireService dataFireService;

	@InjectMocks
	private FireController fireController;
	private MockMvc mockMvc; 

	@BeforeEach
	void setup() { 		
		MockitoAnnotations.openMocks(this);  		
		this.mockMvc = MockMvcBuilders.standaloneSetup(fireController)
			.build();
	}

	@Test
	public void shouldLoadListOfDataFireTest() throws Exception {

		when(dataFireService.listOfDataFire("123"))
			.thenReturn(
				new FireDTO(
					Arrays.asList(
						new FloodDTO(), 
						new FloodDTO(
							"John", 
							"Boyd", 
							"841-874-6512", 
							38,
							new MedicalRecordsInfoDTO(
								Arrays.asList("aznol:350mg", "hydrapermazol:100mg"),
								Arrays.asList("nillacilan")
							)
						)
					), 
					new HashSet<>(Arrays.asList("2", "3"))
				)
			);
		
		mockMvc.perform( 							
			MockMvcRequestBuilders.get("/fire?address=123")	
			.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.listOfPerson[1].firstName").value("John"))
		.andExpect(jsonPath("$.listOfPerson[1].lastName").value("Boyd"))
		.andExpect(jsonPath("$.listOfPerson[1].age").value(38))
		.andExpect(jsonPath("$.listOfPerson[1].phone").value("841-874-6512"))
		.andExpect(jsonPath("$.listOfPerson[1].medicalRecords.medications").isArray())
		.andExpect(jsonPath("$.listOfPerson[1].medicalRecords.medications.length()").value(2))
		.andExpect(jsonPath("$.listOfPerson[1].medicalRecords.medications[0]").value("aznol:350mg"))
		.andExpect(jsonPath("$.listOfPerson[1].medicalRecords.medications[1]").value("hydrapermazol:100mg"))
		.andExpect(jsonPath("$.listOfPerson[1].medicalRecords.allergies").isArray())
		.andExpect(jsonPath("$.listOfPerson[1].medicalRecords.allergies.length()").value(1))
		.andExpect(jsonPath("$.listOfPerson[1].medicalRecords.allergies[0]").value("nillacilan"))
		
		.andExpect(jsonPath("$.numberFireStation[0]").value("2"));
	}
}
