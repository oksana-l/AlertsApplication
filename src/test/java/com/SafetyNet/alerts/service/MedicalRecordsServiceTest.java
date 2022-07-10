package com.SafetyNet.alerts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

	Optional<MedicalRecord> medicalrecord;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		medicalrecord = Optional.of(new MedicalRecord("John", "Boyd", "03/06/1984",
					Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan")));
		
		when(medicalRecordsRepository.findByName("John", "Boyd")).thenReturn(medicalrecord);
	}
	
	@Test
	public void shouldGetAgeOfPersonTest() {
		int ageTest = Period.between(LocalDate.parse(medicalrecord.get().getbirthdate(), 
				DateTimeFormatter.ofPattern("MM/dd/yyyy")), LocalDate.now()).getYears();
		
		Assertions.assertEquals(ageTest, medicalRecordsService.getAgeOfPerson("John", "Boyd"));
	}
	
	@Test
	public void shouldIsMinorTest() {
		Assertions.assertFalse(medicalRecordsService.getAgeOfPerson("John", "Boyd") < 19);
	}
	
	@Test
	public void shouldIsMajorTest() {
		Assertions.assertTrue(medicalRecordsService.getAgeOfPerson("John", "Boyd") >19);
	}
	
	@Test
	public void shouldMedicalrecordFindByNameTest() {
		Assertions.assertFalse(medicalRecordsService.medicalrecordFindByName("John", "Boyd")
				.getMedications().isEmpty());
		Assertions.assertTrue(medicalRecordsService.medicalrecordFindByName("John", "Boyd")
				.getMedications().size() == 2);
		Assertions.assertFalse(medicalRecordsService.medicalrecordFindByName("John", "Boyd")
				.getAllergies().isEmpty());
		Assertions.assertTrue(medicalRecordsService.medicalrecordFindByName("John", "Boyd")
				.getAllergies().size() == 1);
	}
	
	@Test
	public void shouldGetMedicalrecordTest() {
		Assertions.assertEquals(medicalrecord, medicalRecordsService.getMedicalrecord("John Boyd"));
	}
	
	@Test
	public void shouldCreateMedicalRecordTest() throws Exception {
		MedicalRecord medicalrecordToCreate = new MedicalRecord();
		
		when(medicalRecordsRepository.findByName(any(String.class), any(String.class))).thenReturn(medicalrecord);
		medicalRecordsService.createMedicalRecord(medicalrecordToCreate);
		
		verify(medicalRecordsRepository, times(1)).save(medicalrecordToCreate);
		Assertions.assertEquals("John", medicalrecord.get().getFirstName());
		Assertions.assertEquals("Boyd", medicalrecord.get().getLastName());
		Assertions.assertEquals("03/06/1984", medicalrecord.get().getbirthdate());
		Assertions.assertTrue(medicalrecord.get().getMedications().size() == 2);
		Assertions.assertTrue(medicalrecord.get().getAllergies().size() == 1);
	}
	
	@Test
	public void shouldUpdateMedicalRecordTest() {
		
		
	}
	
	@Test
	public void shouldDeleteMedicalRecordTest() {
		doNothing().when(medicalRecordsRepository).delete(any(String.class), any(String.class));
		medicalRecordsService.deleteMedicalRecord("John Boyd");
		verify(medicalRecordsRepository, times(1)).delete("John", "Boyd");		
	}
}

