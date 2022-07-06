package com.SafetyNet.alerts.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.StoreTest;
import com.SafetyNet.alerts.dto.PersonInfoDTO;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.PersonRepository;

public class PersonInfoServiceTest {

	PersonRepository personRepository;
	PersonInfoService personInfoService;
	MedicalRecordService medicalRecordsService;
	StoreTest testStore;
	
	@BeforeEach
	public void setUp() {
		personRepository = mock(PersonRepository.class);
		medicalRecordsService = mock(MedicalRecordService.class);
		personInfoService = new PersonInfoService(medicalRecordsService, personRepository);
	}
	
	@Test
	public void shouldPersonInfoTest() {
		
		when(personRepository.findByFirstNameAndLastName("John", "Boyd"))
		.thenReturn(Optional.of(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com")));
		
		
		PersonInfoDTO personInfo = personInfoService.personInfo("John", "Boyd");
		
		Assertions.assertEquals("John Boyd", personInfo.getName());
	}
}
