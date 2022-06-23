package com.SafetyNet.alerts.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.SafetyNet.alerts.model.MedicalRecord;
import com.SafetyNet.alerts.repository.MedicalRecordRepository;

public class MedicalRecordsServiceTest {
	
	@Mock
	private MedicalRecordRepository medicalRecordsRepository;
	
	@InjectMocks
	private MedicalRecordService medicalRecordsService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void shouldGetAgeOfPersonTest() {
		
		Optional<MedicalRecord> medicalRecords1 = Optional.ofNullable(new MedicalRecord("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan")));
		
		when(medicalRecordsRepository.findByName("John", "Boyd")).thenReturn(medicalRecords1);
		Assertions.assertEquals("date du jour moins 38", medicalRecordsService.getAgeOfPerson("John", "Boyd"));
	}
}
