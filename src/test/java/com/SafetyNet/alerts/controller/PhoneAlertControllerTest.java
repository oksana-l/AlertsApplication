package com.SafetyNet.alerts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.SafetyNet.alerts.service.PhoneAlertService;

public class PhoneAlertControllerTest {

	@Mock
	private PhoneAlertService phoneAlertService;

	@InjectMocks
	private PhoneAlertController phoneAlertController;
	private MockMvc mockMvc; 
	
	Set<String> phones; 

	@BeforeEach
	void setup() {
		phones = new HashSet<>();
		phones.add("841-874-6512");
		phones.add("849-374-7532");
		MockitoAnnotations.openMocks(this);  		
		this.mockMvc = MockMvcBuilders.standaloneSetup(phoneAlertController)
			.build();

	}
	
	@Test
	public void shouldphoneAlertControllerTest() throws Exception {
		when(phoneAlertService.listOfPhonePerNumFireStation("2"))
			.thenReturn(phones);

		mockMvc.perform( 							
				MockMvcRequestBuilders.get("/phoneAlert?firestation=2")	
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
