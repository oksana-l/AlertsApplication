package com.SafetyNet.alerts.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.SafetyNet.alerts.model.MedicalRecords;
import com.SafetyNet.alerts.repository.MedicalRecordsRepository;

public class MedicalRecordsServiceTest {
	
	@Mock
	private MedicalRecordsRepository medicalRecordsRepository;
	
	@InjectMocks
	private MedicalRecordsService medicalRecordsService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void shouldGetAgeOfPersonTest() {
		
		MedicalRecords medicalRecords1 = new MedicalRecords("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));
		
		when(medicalRecordsRepository.findByName("John", "Boyd")).thenReturn(medicalRecords1);
		Assertions.assertEquals(date du jour moins 38, medicalRecordsService.getAgeOfPerson("John", "Boyd"));
	}
}
