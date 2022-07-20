package com.SafetyNet.alerts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.dto.FireDTO;
import com.SafetyNet.alerts.dto.FirePersonDTO;
import com.SafetyNet.alerts.dto.MedicalRecordsInfoDTO;
import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

public class FireServiceTest {
	
	FireStationRepository fireStationRepository;
	PersonRepository personRepository;
	MedicalRecordService medicalRecordService;
	FireService fireservice;	
	
	Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513","bstel@email.com");
	Person person3 = new Person("Peter", "Duncan", "1509 Culver St", "Culver", "97451", "841-874-6512", "drk@email.com");
	List<Person> persons = Arrays.asList(person1, person2, person3);
	
	FireStation firestation = new FireStation("1509 Culver St", "1");
	List<FireStation> firestations = Arrays.asList(firestation);
	
	MedicalRecordsInfoDTO medicalRecordsInfo = new MedicalRecordsInfoDTO(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), 
			Arrays.asList("nillacilan"));
	
	@BeforeEach
	public void setUp() {
		fireStationRepository = mock(FireStationRepository.class);
		personRepository = mock(PersonRepository.class);
		medicalRecordService = mock(MedicalRecordService.class);
		fireservice = new FireService(fireStationRepository, personRepository, medicalRecordService);
	}

	@Test
	public void shouldListOfFirePersonsTest() {
		when(personRepository.findByAddress(any())).thenReturn(persons);
		when(medicalRecordService.medicalrecordFindByFirstNameAndLastName("John", "Boyd"))
			.thenReturn(medicalRecordsInfo);
		when(medicalRecordService.getAgeOfPerson("John", "Boyd")).thenReturn(38);
		List<FirePersonDTO> listOfPerson = fireservice.listOfFirePersons("1509 Culver St");
		
		Assertions.assertEquals(3, listOfPerson.size());
		Assertions.assertEquals("John", listOfPerson.get(0).getFirstName());
		Assertions.assertEquals("Boyd", listOfPerson.get(0).getLastName());
		Assertions.assertEquals(38, listOfPerson.get(0).getAge());
		Assertions.assertEquals("841-874-6512", listOfPerson.get(0).getPhone());
		Assertions.assertEquals(2, listOfPerson.get(0).getMedical().getMedications().size());
		Assertions.assertEquals(1, listOfPerson.get(0).getMedical().getAllergies().size());
		
	}
	
	@Test
	public void shouldlistOfDataFireTest() {
		when(fireStationRepository.findByAddress(any())).thenReturn(firestations);
		FireDTO listOfDataFire = fireservice.listOfDataFire("1509 Culver St");
		
		Assertions.assertEquals(1, listOfDataFire.getNumberFireStation().size());
	}
}
