package com.SafetyNet.alerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.StoreTest;
import com.SafetyNet.alerts.dto.ChildAlertDTO;
import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.model.Store;
import com.SafetyNet.alerts.repository.MedicalRecordRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

public class ChildAlertServiceTest {
	
	PersonRepository personRepository;
	MedicalRecordRepository medicalRecordRepository;
	MedicalRecordService medicalRecordService;
	ChildAlertService childAlertService;

	@BeforeEach
	public void setUp() {
		Store testStore = StoreTest.testStore();
		personRepository = new PersonRepository(testStore);
		medicalRecordRepository = new MedicalRecordRepository(testStore);
		medicalRecordService = new MedicalRecordService(medicalRecordRepository);
		childAlertService = new ChildAlertService(personRepository, medicalRecordService);
	}
	
	@Test
	public void shouldListOfChildPerAddressTest() {
		List<ChildAlertDTO> listOfChildPerAddress = childAlertService
				.listOfChildPerAddress("1509 Culver St");
		int ageTest = Period.between(LocalDate.parse("12/02/2010", 
				DateTimeFormatter.ofPattern("MM/dd/yyyy")), LocalDate.now()).getYears();
		
		Assertions.assertEquals(1, listOfChildPerAddress.size());
		Assertions.assertEquals("Jacob", listOfChildPerAddress.get(0).getFirstName());
		Assertions.assertEquals("Boyd", listOfChildPerAddress.get(0).getLastName());
		Assertions.assertEquals(ageTest, listOfChildPerAddress.get(0).getAge());
	}
	
	@Test
	public void shouldlistOfAdultPerAddressTest() {
		List<PersonDTO> listOfAdultPerAddress = childAlertService
				.listOfAdultPerAddress("1509 Culver St");
		
		Assertions.assertEquals(1, listOfAdultPerAddress.size());
		Assertions.assertEquals("John", listOfAdultPerAddress.get(0).getFirstName());
		Assertions.assertEquals("Boyd", listOfAdultPerAddress.get(0).getLastName());
	}
}
