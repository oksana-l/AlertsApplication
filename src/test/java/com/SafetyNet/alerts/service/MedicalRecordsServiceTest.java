package com.SafetyNet.alerts.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
		
		Optional<MedicalRecord> medicalRecords1 = Optional.ofNullable(new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan")));
		
		when(medicalRecordsRepository.findByName("John", "Boyd")).thenReturn(medicalRecords1);
		
		int ageTest = Period.between(LocalDate.parse(medicalRecords1.get().getbirthdate(), 
				DateTimeFormatter.ofPattern("MM/dd/yyyy")), LocalDate.now()).getYears();
		
		Assertions.assertEquals(ageTest, medicalRecordsService.getAgeOfPerson("John", "Boyd"));
		
	}
}

