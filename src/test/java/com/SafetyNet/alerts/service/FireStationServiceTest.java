package com.SafetyNet.alerts.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.StoreTest;
import com.SafetyNet.alerts.dto.FirestationDTO;
import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.dto.PersonPerStationDTO;
import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.model.Store;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.MedicalRecordRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

public class FireStationServiceTest {

	FireStationRepository fireStationRepository;
	PersonRepository personRepository;
	MedicalRecordRepository medicalRecordRepository;
	FireStationService fireStationService;
	FireStation firestation;
	List<FireStation> firestations;
	
	@BeforeEach
	public void setUp() {
		Store testStore = StoreTest.testStore();
		fireStationRepository = new FireStationRepository(testStore);
		personRepository = new PersonRepository(testStore);
		medicalRecordRepository = new MedicalRecordRepository(testStore);
		fireStationService = new FireStationService(fireStationRepository, 
								personRepository, new MedicalRecordService(medicalRecordRepository));
		firestations = testStore.getFirestations();
		firestation = new FireStation("146 Liberty St","4");
	}
	
	@Test
	public void shouldInfoPersonsPerStationTest() {
		List<PersonPerStationDTO> listOfPersons = fireStationService.infoPersonsPerStation("1");
		Assertions.assertEquals(2, listOfPersons.size());
	}
	
	@Test
	public void shouldPersonPerStationTest() {

		List<PersonDTO> listOfPersons = fireStationService.personPerStation("1");
		Assertions.assertEquals(2, listOfPersons.size());
	}
	
	@Test
	public void shouldPersonsPerStationAndAgeTest() {
		FirestationDTO infoTest = fireStationService.personsPerStationAndAge("1");
		
		Assertions.assertEquals(1, infoTest.getNumberOfMajors());
		Assertions.assertEquals(1, infoTest.getNumberOfMinors());
		Assertions.assertEquals(2,  infoTest.getPersonPerStationDTO().size());
	}
	
	@Test
	public void shouldExceptionCreateFireStationTest() throws Exception {
		
		Assertions.assertThrows(Exception.class, () -> fireStationService
				.createFireStation(firestations.get(0)));
	}
		
	@Test
	public void shouldCreateFireStationTest() throws Exception {
		fireStationService.createFireStation(firestation);
		
		Assertions.assertEquals(4, firestations.size());
		Assertions.assertEquals("4", firestations.get(3).getStation());
		Assertions.assertEquals("146 Liberty St", firestations.get(3).getAddress());
	}
	
	@Test
	public void shouldUpdateFireStationTest() {
		fireStationService.updateFireStation(firestations.get(2).getAddress(), firestation);
		
		Assertions.assertEquals("146 Liberty St", firestations.get(2).getAddress());
		Assertions.assertEquals("4", firestations.get(2).getStation());
		Assertions.assertEquals(3, firestations.size());	
	}
	
	@Test
	public void shouldDeleteFireStationTest() {
		fireStationService.deleteFireStation(firestations.get(0).getAddress());
		
		Assertions.assertEquals(2, firestations.size());
	}
}
