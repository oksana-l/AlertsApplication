package com.SafetyNet.alerts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;

import com.SafetyNet.alerts.dto.UpdateMedicalRecordRequest;
import com.SafetyNet.alerts.model.MedicalRecord;
import com.SafetyNet.alerts.repository.MedicalRecordRepository;

public class MedicalRecordsServiceTest {
	
	MedicalRecordRepository medicalRecordsRepository;
	MedicalRecordService medicalRecordsService;
	Optional<MedicalRecord> medicalrecord;
	
	@BeforeEach
	public void setUp() {
		medicalRecordsRepository = mock(MedicalRecordRepository.class);
		medicalRecordsService = new MedicalRecordService(medicalRecordsRepository);
		medicalrecord = Optional.of(new MedicalRecord("John", "Boyd", "03/06/1984",
					Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan")));
		
		when(medicalRecordsRepository.findByName("John", "Boyd")).thenReturn(medicalrecord);
	}
	
	@Test
	public void shouldGetAgeOfPersonTest() {
		Assertions.assertEquals(38, medicalRecordsService.getAgeOfPerson("John", "Boyd"));
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
		Assertions.assertFalse(medicalRecordsService.medicalrecordFindByFirstNameAndLastName("John", "Boyd")
				.getMedications().isEmpty());
		Assertions.assertTrue(medicalRecordsService.medicalrecordFindByFirstNameAndLastName("John", "Boyd")
				.getMedications().size() == 2);
		Assertions.assertFalse(medicalRecordsService.medicalrecordFindByFirstNameAndLastName("John", "Boyd")
				.getAllergies().isEmpty());
		Assertions.assertTrue(medicalRecordsService.medicalrecordFindByFirstNameAndLastName("John", "Boyd")
				.getAllergies().size() == 1);
	}
	
	@Test
	public void shouldGetMedicalrecordTest() {
		Optional<MedicalRecord> getMR = medicalRecordsService.getMedicalrecord("John Boyd");
		Assertions.assertEquals(medicalrecord.get(), getMR.get());
	}
	
	@Test
	public void shouldCreateMedicalRecordTest() throws Exception {
		when(medicalRecordsRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
		
		MedicalRecord medicalrecordToSave = new MedicalRecord("Peter", "Duncan", "09/06/2000", 
				Arrays.asList(), Arrays.asList("shellfish"));
		MedicalRecord medicalrecordSave = medicalRecordsService.createMedicalRecord(medicalrecordToSave);
		
		verify(medicalRecordsRepository, times(1)).save(medicalrecordToSave);
		Assertions.assertEquals(0, medicalrecordSave.getMedications().size());
		Assertions.assertEquals(1, medicalrecordSave.getAllergies().size());
	}
	
	@Test
	public void shouldUpdateMedicalRecordTest() {
		when(medicalRecordsRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
		
		UpdateMedicalRecordRequest medicalrecordToUpdate = new UpdateMedicalRecordRequest("12/02/2010", 
				Arrays.asList("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), Arrays.asList());
		Optional<MedicalRecord> medicalRecordUpdate = medicalRecordsService
				.updateMedicalRecord("Johnb Boyd", medicalrecordToUpdate);
		
		//verify(medicalRecordsRepository.save(null), times(1)).save(medicalRecordUpdate.get());
		Assertions.assertEquals(3, medicalRecordUpdate.get().getMedications().size());
		Assertions.assertEquals(0, medicalRecordUpdate.get().getAllergies().size());
	}
	
	@Test
	public void shouldDeleteMedicalRecordTest() {
		medicalRecordsService.deleteMedicalRecord("John Boyd");
		verify(medicalRecordsRepository, times(1)).delete("John", "Boyd");		
	}
}

