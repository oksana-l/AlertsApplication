package com.SafetyNet.alerts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

public class PhoneAlertServceTest {

	FireStationRepository fireStationRepository;
	PersonRepository personRepository;
	PhoneAlertService phoneAlertService;

	Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513","bstel@email.com");
	Person person3 = new Person("Peter", "Duncan", "1509 Culver St", "Culver", "97451", "841-874-6512", "drk@email.com");
	List<Person> persons = Arrays.asList(person1, person2, person3);
	
	FireStation firestation1 = new FireStation("1509 Culver St", "1");
	FireStation firestation2 = new FireStation("834 Binoc Ave", "2");
	FireStation firestation3 = new FireStation("947 E. Rose Dr", "3");
	List<FireStation> firestations = Arrays.asList(firestation1, firestation2, firestation3);
	
	@BeforeEach
	public void setUp() {
		fireStationRepository = mock(FireStationRepository.class);
		personRepository = mock(PersonRepository.class);
		phoneAlertService = new PhoneAlertService(fireStationRepository, personRepository);
	}
	
	@Test
	public void shouldListOfPhonePerNumFireStationTest() {
		when(fireStationRepository.findByStation("1")).thenReturn(firestations);
		when(personRepository.findByAddressesIn(any())).thenReturn(persons);
		
		Set<String> listOfPhone = phoneAlertService.listOfPhonePerNumFireStation("1");
		
		Assertions.assertEquals(2, listOfPhone.size());
	}
}
