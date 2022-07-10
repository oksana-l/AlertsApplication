package com.SafetyNet.alerts.service;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

public class PhoneAlertServceTest {

	FireStationRepository fireStationRepository;
	PersonRepository personRepository;
	PhoneAlertService phoneAlertService;
	
	@BeforeEach
	public void setUp() {
		fireStationRepository = mock(FireStationRepository.class);
		personRepository = mock(PersonRepository.class);
		phoneAlertService = new PhoneAlertService(fireStationRepository, personRepository);
	}
	
	@Test
	public void shouldListOfPhonePerNumFireStationTest() {
		
	}
}
