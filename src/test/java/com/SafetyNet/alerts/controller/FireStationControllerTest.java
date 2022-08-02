package com.SafetyNet.alerts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.SafetyNet.alerts.dto.FirestationDTO;
import com.SafetyNet.alerts.dto.PersonPerStationDTO;
import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.service.FireStationService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FireStationControllerTest {

	@Mock
	FireStationService fireStationService;
	
	@InjectMocks
	FireStationController fireStationController;
	private MockMvc mockMvc;
	
	PersonPerStationDTO person = new PersonPerStationDTO(
			"John", "Boyd", "1509 Culver St", "841-874-6512");
	List<PersonPerStationDTO> listOfPersons = new ArrayList<PersonPerStationDTO>();
	FirestationDTO firestationDTO = new FirestationDTO(listOfPersons, 2, 1);
	FireStation firestation = new FireStation("1509 Culver St", "1");
	FireStation updatedFirestation = new FireStation("644 Gershwin Cir", "3");
	
	@BeforeEach
	void setup() { 		
		MockitoAnnotations.openMocks(this);  		
		this.mockMvc = MockMvcBuilders.standaloneSetup(fireStationController)
			.build();
	}
	
	@Test
	public void shouldPersonsPerStationTest() throws Exception {
		when(fireStationService.personsPerStationAndAge("1")).thenReturn(firestationDTO);
		
		
		mockMvc.perform( 							
			MockMvcRequestBuilders.get("/firestation?stationNumber=1")	
			.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.numberOfMajors").value("2"))
		.andExpect(jsonPath("$.numberOfMinors").value("1"));
	}
	
	@Test
	public void shouldCreateFireStationTest() throws Exception {
		when(fireStationService.createFireStation(any()))
		.then(AdditionalAnswers.returnsFirstArg());
		
		mockMvc.perform( 							
				MockMvcRequestBuilders.post("/firestation")	
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(firestation)))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.address").value("1509 Culver St"))
			.andExpect(jsonPath("$.station").value("1"));
	}
	
	@Test
	public void shouldUpdateFireStationTest() throws Exception {
		when(fireStationService.updateFireStation(any(), any()))
			.thenReturn(Optional.of(updatedFirestation));
		
		mockMvc.perform( 							
				MockMvcRequestBuilders.put("/firestation/{address}", "1509 Culver St")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\": \"1509 Culver St\"}"))
				.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.address").value("644 Gershwin Cir"))
			.andExpect(jsonPath("$.station").value("3"));
	}
	
	@Test
	public void shouldDeleteFireStationTest() throws Exception {
		mockMvc.perform( 							
				MockMvcRequestBuilders.delete("/firestation/{address}", "1509 Culver St"))
		        .andExpect(status().isAccepted());
	}
}
