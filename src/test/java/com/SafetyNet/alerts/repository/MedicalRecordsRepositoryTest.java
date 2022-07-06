package com.SafetyNet.alerts.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.StoreTest;
import com.SafetyNet.alerts.model.MedicalRecord;
import com.SafetyNet.alerts.model.Store;

public class MedicalRecordsRepositoryTest {
	
	MedicalRecordRepository medicalRecordRepository;
	List<MedicalRecord> medicalrecords;
	
	@BeforeEach
	public void setUp() {
		Store testStore = StoreTest.testStore();
		medicalRecordRepository = new MedicalRecordRepository(testStore);	
		medicalrecords = testStore.getMedicalrecords();
	}
	
	@Test 
	public void shouldFindAllTest() {
		List<MedicalRecord> medicalrecordsTest = medicalRecordRepository.findAll();
		Optional<MedicalRecord> medicalrecord = medicalRecordRepository.findByName("Peter", "Duncan");
		
		Assertions.assertTrue(medicalrecordsTest.size() == 3);
		Assertions.assertEquals(medicalrecord, Optional.of(medicalrecordsTest.get(2)));
	}
	
	@Test
	public void shouldFindByFirstNameTest() {
		List<MedicalRecord> medicalrecordsTest = medicalRecordRepository.findByFirstName("John");
		
		Assertions.assertTrue(medicalrecordsTest.size() == 1);
		Assertions.assertEquals("John", medicalrecordsTest.get(0).getFirstName());
	}
	
	@Test
	public void shouldBirthDateTest() {
		
		Assertions.assertEquals("09/06/2000", medicalRecordRepository.birthDate("Peter", "Duncan"));
	}
	
	@Test
	public void shouldSaveTest() {
		MedicalRecord medicalrecordToSave = new MedicalRecord("Hanna", "Duncan", "13/07/1989", 
				Arrays.asList("aznol:50mg"), Arrays.asList("pillacilan", "penicilline"));
		medicalRecordRepository.save(medicalrecordToSave);
		
		Assertions.assertTrue(medicalrecords.size() == 4);
		Assertions.assertEquals("Hanna", medicalrecords.get(3).getFirstName());
		Assertions.assertEquals("Duncan", medicalrecords.get(3).getLastName());
		Assertions.assertEquals("13/07/1989", medicalrecords.get(3).getbirthdate());
		Assertions.assertEquals(Arrays.asList("aznol:50mg"), medicalrecords.get(3).getMedications());
		Assertions.assertEquals(Arrays.asList("pillacilan", "penicilline"), medicalrecords.get(3).getAllergies());
	}

	@Test
	public void shouldDeleteTest() {
		medicalRecordRepository.delete("Peter", "Duncan");
		
		Assertions.assertTrue(medicalrecords.size() == 2);
	}
}
