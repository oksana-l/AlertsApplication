package com.SafetyNet.alerts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
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

import com.SafetyNet.alerts.dto.UpdateMedicalRecordRequest;
import com.SafetyNet.alerts.model.MedicalRecord;
import com.SafetyNet.alerts.service.MedicalRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MedicalRecordControllerTest {

	@Mock
	private MedicalRecordService medicalRecordService;
	
	@InjectMocks
	private MedicalRecordController medicalRecordController;
	private MockMvc mockMvc;
	
	MedicalRecord medicalrecord = new MedicalRecord("John", "Boyd", "03/06/1984", 
			Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), 
			Arrays.asList("nillacilan"));;
	MedicalRecord medicalrecordUpdate = new MedicalRecord("Peter", "Duncan", "09/06/2000", 
			Arrays.asList(), 
			Arrays.asList("shellfish"));;
	UpdateMedicalRecordRequest medicalrecordToUpdate;
	
	@BeforeEach
	void setup() { 		
		MockitoAnnotations.openMocks(this);  		
		this.mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordController)
			.build();
	}
	
	@Test
	public void shouldCreateMedicalRecordTest() throws Exception {
		when(medicalRecordService.createMedicalRecord(any()))
			.then(AdditionalAnswers.returnsFirstArg());
		
		mockMvc.perform( 							
				MockMvcRequestBuilders.post("/medicalRecord")	
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(medicalrecord)))
			.andDo(print())
			.andExpect(status().isCreated());
			//.andExpect(jsonPath("$.firstName").value("Thomas"));
	}
	
	@Test
	public void shouldUpdateMedicalRecordTest() throws Exception {
		when(medicalRecordService.updateMedicalRecord(any(), any()))
		.thenReturn(Optional.of(medicalrecordUpdate));
		
		mockMvc.perform( 							
				MockMvcRequestBuilders.put("/medicalRecord/{name}", "John Boyd")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\": \"1509 Culver St\"}"))
				.andDo(print())
			.andExpect(status().isOk());
			//.andExpect(jsonPath("$.address").value("1509 Culver St"));
	}
	
	@Test
	public void shouldDeleteMedicalRecord() throws Exception {
		mockMvc.perform( 							
				MockMvcRequestBuilders.delete("/medicalRecord/{name}", "Thomas Pasquier"))
		        .andExpect(status().isAccepted());
	}
}
