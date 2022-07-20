package com.SafetyNet.alerts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.SafetyNet.alerts.dto.UpdatePersonRequest;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
	
	@Mock
	private PersonService dataPersonService;
	
	@InjectMocks
	private PersonController personController;
	private MockMvc mockMvc;
	
	Person person = new Person("Thomas", "Pasquier", "644 Gershwin Cir", "Denver",
			"98651", "841-874-3612", "drk@email.com");
	Person personUpdate = new Person("Thomas", "Pasquier", "1509 Culver St", "Culver", 
			"97451", "841-874-6512", "drk@email.com");
	UpdatePersonRequest personToUpdate;
	
	@BeforeEach
	void setup() { 		
		MockitoAnnotations.openMocks(this);  		
		this.mockMvc = MockMvcBuilders.standaloneSetup(personController)
			.build();
	}
	
	@Test
	public void shouldCreatePersonTest() throws Exception {
		when(dataPersonService.createPerson(any()))
		.then(AdditionalAnswers.returnsFirstArg());
		
		mockMvc.perform( 							
				MockMvcRequestBuilders.post("/person")	
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(person)))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.firstName").value("Thomas"));
	}
	
	@Test
	public void shouldUpdatePerson() throws JsonProcessingException, Exception {
		when(dataPersonService.updatePerson(any(), any()))
		.thenReturn(Optional.of(personUpdate));
		
		mockMvc.perform( 							
				MockMvcRequestBuilders.put("/person/{name}", "Thomas Pasquier")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\": \"1509 Culver St\"}"))
				.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.address").value("1509 Culver St"))
			.andExpect(jsonPath("$.city").value("Culver"))
			.andExpect(jsonPath("$.zip").value("97451"))
			.andExpect(jsonPath("$.phone").value("841-874-6512"));
	}
	
	@Test
	public void shouldDeletePersonTest() throws Exception {
		mockMvc.perform( 							
				MockMvcRequestBuilders.delete("/person/{name}", "Thomas Pasquier"))
		        .andExpect(status().isAccepted());
	}
}
