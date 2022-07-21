package com.SafetyNet.alerts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;

import com.SafetyNet.alerts.dto.FirestationDTO;
import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.dto.PersonPerStationDTO;
import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

public class FireStationServiceTest {

	FireStationRepository fireStationRepository;
	PersonRepository personRepository;
	MedicalRecordService medicalRecordService;
	FireStationService fireStationService;
	FireStation firestation;
	List<FireStation> firestations;
	Set<String> addresses;
	
	Person person1 = new Person("John", "Boyd", "146 Liberty St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	Person person2 = new Person("Jacob", "Boyd", "146 Liberty St", "Culver", "97451", "841-874-6513","bstel@email.com");
	Person person3 = new Person("Peter", "Duncan", "146 Liberty St", "Culver", "97451", "841-874-6512", "drk@email.com");
	List<Person> persons = Arrays.asList(person1, person2, person3);
	
	@BeforeEach
	public void setUp() {
		fireStationRepository = mock(FireStationRepository.class);
		personRepository = mock(PersonRepository.class);
		medicalRecordService = mock(MedicalRecordService.class);
		fireStationService = new FireStationService(fireStationRepository, 
								personRepository, medicalRecordService);
		firestation = new FireStation("146 Liberty St","4");
		firestations = Arrays.asList(firestation);
		addresses = Sets.set("146 Liberty St");
	}
	
	@Test
	public void shouldInfoPersonsPerStationTest() {
		when(fireStationRepository.findByStation("4")).thenReturn(firestations);
		when(personRepository.findByAddressIn(addresses)).thenReturn(persons);
		
		List<PersonPerStationDTO> listOfPersons = fireStationService.infoPersonsPerStation("4");
		
		Assertions.assertEquals(3, listOfPersons.size());
	}
	
	@Test
	public void shouldPersonPerStationTest() {
		when(fireStationRepository.findByStation("4")).thenReturn(firestations);
		when(personRepository.findByAddressIn(addresses)).thenReturn(persons);
		List<PersonDTO> listOfPersons = fireStationService.personPerStation("4");
		Assertions.assertEquals(3, listOfPersons.size());
	}
	
	@Test
	public void shouldPersonsPerStationAndAgeTest() {
		when(fireStationRepository.findByStation("4")).thenReturn(firestations);
		when(personRepository.findByAddressIn(addresses)).thenReturn(persons);
		when(medicalRecordService.getAgeOfPerson("John", "Boyd")).thenReturn(38);
		when(medicalRecordService.getAgeOfPerson("Jacob", "Boyd")).thenReturn(12);
		when(medicalRecordService.getAgeOfPerson("Peter", "Duncan")).thenReturn(29);
		
		
		FirestationDTO infoTest = fireStationService.personsPerStationAndAge("4");
		
		Assertions.assertEquals(2, infoTest.getNumberOfMajors());
		Assertions.assertEquals(1, infoTest.getNumberOfMinors());
		Assertions.assertEquals(3,  infoTest.getPersonPerStationDTO().size());
	}
	
	@Test
	public void shouldExceptionCreateFireStationTest() throws Exception {
		when(fireStationRepository.getFirestation("146 Liberty St"))
			.thenReturn(Optional.of(firestation));
		
		Assertions.assertThrows(Exception.class, () -> fireStationService
				.createFireStation(firestation));
	}
		
	@Test
	public void shouldCreateFireStationTest() throws Exception {
		when(fireStationRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
		
		FireStation firestationSave = fireStationService.createFireStation(firestation);

		verify(fireStationRepository, times(1)).save(firestation);
		Assertions.assertEquals("4", firestationSave.getStation());
		Assertions.assertEquals("146 Liberty St", firestationSave.getAddress());
	}
	
	@Test
	public void shouldUpdateFireStationTest() {
		when(fireStationRepository.getFirestation("146 Liberty St"))
			.thenReturn(Optional.of(firestation));
		when(fireStationRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
		
		FireStation firestationToUpdate = new FireStation("644 Gershwin Cir", "3");
		Optional<FireStation> firestationUpdate = fireStationService
				.updateFireStation("146 Liberty St", firestationToUpdate);

		Assertions.assertEquals("644 Gershwin Cir", firestationUpdate.get().getAddress());
		Assertions.assertEquals("3", firestationUpdate.get().getStation());	
	}
	
	@Test
	public void shouldDeleteFireStationTest() {
		fireStationService.deleteFireStation("146 Liberty St");

		verify(fireStationRepository, times(1)).delete("146 Liberty St");
	}
}
