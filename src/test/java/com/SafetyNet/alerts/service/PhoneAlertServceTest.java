package com.SafetyNet.alerts.service;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.StoreTest;
import com.SafetyNet.alerts.model.Store;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

public class PhoneAlertServceTest {

	FireStationRepository fireStationRepository;
	PersonRepository personRepository;
	PhoneAlertService phoneAlertService;
	
	@BeforeEach
	public void setUp() {
		Store testStore = StoreTest.testStore();
		fireStationRepository = new FireStationRepository(testStore);
		personRepository = new PersonRepository(testStore);
		phoneAlertService = new PhoneAlertService(fireStationRepository, personRepository);
	}
	
	@Test
	public void shouldListOfPhonePerNumFireStationTest() {
		Set<String> listOfPhone = phoneAlertService.listOfPhonePerNumFireStation("1");
		
		Assertions.assertEquals(2, listOfPhone.size());
	}
}
