package com.SafetyNet.alerts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.dto.ChildAlertDTO;
import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.PersonRepository;

public class ChildAlertServiceTest {
	
	PersonRepository personRepository;
	MedicalRecordService medicalRecordService;
	ChildAlertService childAlertService;
	
	Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513","bstel@email.com");
	Person person3 = new Person("Peter", "Duncan", "1509 Culver St", "Culver", "97451", "841-874-6512", "drk@email.com");
	List<Person> persons = Arrays.asList(person1, person2, person3);
	List<Person> adults = Arrays.asList(person1, person3);
	List<Person> children = Arrays.asList(person2);

	@BeforeEach
	public void setUp() {
		personRepository = mock(PersonRepository.class);
		medicalRecordService = mock(MedicalRecordService.class);
		childAlertService = new ChildAlertService(personRepository, medicalRecordService);
	}
	
	@Test
	public void shouldListOfChildPerAddressTest() {
		when(personRepository.findByAddress("1509 Culver St")).thenReturn(children);
		when(medicalRecordService.isMinor(any(), any())).thenReturn(true);
		when(medicalRecordService.getAgeOfPerson(any(), any())).thenReturn(12);
		
		List<ChildAlertDTO> listOfChildPerAddress = childAlertService
				.listOfChildPerAddress("1509 Culver St");
		
		Assertions.assertEquals(1, listOfChildPerAddress.size());
		Assertions.assertEquals("Jacob", listOfChildPerAddress.get(0).getFirstName());
		Assertions.assertEquals("Boyd", listOfChildPerAddress.get(0).getLastName());
		Assertions.assertEquals(12, listOfChildPerAddress.get(0).getAge());
	}
	
	@Test
	public void shouldlistOfAdultPerAddressTest() {
		when(personRepository.findByAddress("1509 Culver St")).thenReturn(adults);
		when(medicalRecordService.isMajor(any(), any())).thenReturn(true);
		List<PersonDTO> listOfAdultPerAddress = childAlertService
				.listOfAdultPerAddress("1509 Culver St");
		
		Assertions.assertEquals(2, listOfAdultPerAddress.size());
		Assertions.assertEquals("John", listOfAdultPerAddress.get(0).getFirstName());
		Assertions.assertEquals("Boyd", listOfAdultPerAddress.get(0).getLastName());
	}
}
