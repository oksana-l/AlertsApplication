package com.SafetyNet.alerts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.SafetyNet.alerts.dto.FamilyDTO;
import com.SafetyNet.alerts.dto.MedicalRecordsInfoDTO;
import com.SafetyNet.alerts.dto.FloodDTO;
import com.SafetyNet.alerts.service.FloodService;

public class FloodControllerTest {

	@Mock
	private FloodService floodService;

	@InjectMocks
	private FloodController floodController;
	private MockMvc mockMvc; 
	
	List<FloodDTO> familyList = Arrays.asList(
			new FloodDTO("John", "Boyd", "841-874-6512", 38, 
				new MedicalRecordsInfoDTO(
					Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), 
					Arrays.asList("nillacilan"))),
			new FloodDTO("Jacob", "Boyd", "841-874-6513", 12,
				new MedicalRecordsInfoDTO(
					Arrays.asList("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), 
					Arrays.asList())));
	FamilyDTO family = new FamilyDTO(familyList, "1509 Culver St");
	List<String> stations =  Arrays.asList("2");

	@BeforeEach
	void setup() { 		
		MockitoAnnotations.openMocks(this);  		
		this.mockMvc = MockMvcBuilders.standaloneSetup(floodController)
			.build();
	}

	@Test
	public void shouldListOfFamilyTest() throws Exception {
		when(floodService.listOfFamily(stations))
			.thenReturn(Arrays.asList(family));

		mockMvc.perform( 							
				MockMvcRequestBuilders.get("/flood/stations?stations=[2]")	
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
