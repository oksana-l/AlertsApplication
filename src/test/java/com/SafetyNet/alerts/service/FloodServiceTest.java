package com.SafetyNet.alerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.StoreTest;
import com.SafetyNet.alerts.dto.FamilyDTO;
import com.SafetyNet.alerts.dto.PersonFloodDTO;
import com.SafetyNet.alerts.model.MedicalRecord;
import com.SafetyNet.alerts.model.Store;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.MedicalRecordRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

public class FloodServiceTest {

	FireStationRepository fireStationRepository;
	PersonRepository personRepository;
	MedicalRecordRepository medicalRecordRepository;
	MedicalRecordService medicalRecordService;
	FloodService floodService;
	MedicalRecord medicalrecord = new MedicalRecord();
	
	@BeforeEach
	public void setUp() {
		Store testStore = StoreTest.testStore();
		fireStationRepository = new FireStationRepository(testStore);
		personRepository = new PersonRepository(testStore);
		medicalRecordRepository = new MedicalRecordRepository(testStore);
		floodService = new FloodService(fireStationRepository, 
				personRepository, new MedicalRecordService(medicalRecordRepository));
	}
	
	@Test
	public void shouldListOfFamilyTest() {
		List<String> stationNumbers = Arrays.asList("1", "2", "3");
		List<FamilyDTO> listOfFamilies = floodService.listOfFamily(stationNumbers);
		List<PersonFloodDTO> family = listOfFamilies.get(0).getFamily();

		int ageTest = Period.between(LocalDate.parse("03/06/1984", 
				DateTimeFormatter.ofPattern("MM/dd/yyyy")), LocalDate.now()).getYears();
		
		Assertions.assertEquals(3, listOfFamilies.size());
		Assertions.assertEquals(2,  family.size());
		Assertions.assertEquals("John", family.get(0).getFirstName());
		Assertions.assertEquals("Boyd", family.get(0).getLastName());
		Assertions.assertEquals(ageTest, family.get(0).getAge());
		Assertions.assertEquals(2, family.get(0).getMedicalRecords().getMedications().size());
		Assertions.assertEquals(1, family.get(0).getMedicalRecords().getAllergies().size());
	}

}
