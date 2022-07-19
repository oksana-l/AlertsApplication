package com.SafetyNet.alerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.StoreTest;
import com.SafetyNet.alerts.dto.FireDTO;
import com.SafetyNet.alerts.dto.FirePersonDTO;
import com.SafetyNet.alerts.model.Store;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.MedicalRecordRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

public class FireServiceTest {
	
	FireStationRepository fireStationRepository;
	PersonRepository personRepository;
	MedicalRecordRepository medicalRecordRepository;
	MedicalRecordService medicalRecordService;
	FireService fireservice;
	
	@BeforeEach
	public void setUp() {
		Store testStore = StoreTest.testStore();
		fireStationRepository = new FireStationRepository(testStore);
		personRepository = new PersonRepository(testStore);
		medicalRecordRepository = new MedicalRecordRepository(testStore);
		fireservice = new FireService(fireStationRepository, personRepository, 
				new MedicalRecordService(medicalRecordRepository));
	}

	@Test
	public void shouldListOfFirePersonsTest() {
		List<FirePersonDTO> listOfPerson = fireservice.listOfFirePersons("1509 Culver St");
		FirePersonDTO person = listOfPerson.get(1);
		int ageTest = Period.between(LocalDate.parse("12/02/2010", 
				DateTimeFormatter.ofPattern("MM/dd/yyyy")), LocalDate.now()).getYears();
		
		Assertions.assertEquals(2, listOfPerson.size());
		Assertions.assertEquals("Jacob", person.getFirstName());
		Assertions.assertEquals("Boyd", person.getLastName());
		Assertions.assertEquals(ageTest, person.getAge());
		Assertions.assertEquals("841-874-6513", person.getPhone());
		Assertions.assertEquals(3, person.getMedical().getMedications().size());
		Assertions.assertEquals(0, person.getMedical().getAllergies().size());
		
	}
	
	@Test
	public void shouldlistOfDataFireTest() {
		FireDTO listOfDataFire = fireservice.listOfDataFire("1509 Culver St");
		
		Assertions.assertEquals(2, listOfDataFire.getListOfFirePerson().size());
		Assertions.assertEquals(1, listOfDataFire.getNumberFireStation().size());
	}
}
