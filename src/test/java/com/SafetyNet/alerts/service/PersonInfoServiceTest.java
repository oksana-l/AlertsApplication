package com.SafetyNet.alerts.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.dto.MedicalRecordsInfoDTO;
import com.SafetyNet.alerts.dto.PersonInfoDTO;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.PersonRepository;

public class PersonInfoServiceTest {

	PersonRepository personRepository;
	PersonInfoService personInfoService;
	MedicalRecordService medicalRecordsService;
	
	@BeforeEach
	public void setUp() {
		personRepository = mock(PersonRepository.class);
		medicalRecordsService = mock(MedicalRecordService.class);
		personInfoService = new PersonInfoService(medicalRecordsService, personRepository);
	}
	
	@Test
	public void shouldPersonInfoTest() {
		when(personRepository.findByFirstNameAndLastName("John", "Boyd"))
			.thenReturn(Optional.of(new Person("John", "Boyd", "1509 Culver St", null, 
				null, null, "jaboyd@email.com")));	
		when(medicalRecordsService.getAgeOfPerson("John", "Boyd")).thenReturn(38);
		when(medicalRecordsService.medicalrecordFindByFirstNameAndLastName("John", "Boyd"))
			.thenReturn(new MedicalRecordsInfoDTO(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), 
				Arrays.asList("nillacilan")));
		PersonInfoDTO personInfo = personInfoService.personInfo("John", "Boyd");
		
		Assertions.assertEquals("John Boyd", personInfo.getName());
		Assertions.assertEquals("1509 Culver St", personInfo.getAddress());
		Assertions.assertEquals(38, personInfo.getAge());
		Assertions.assertEquals("jaboyd@email.com", personInfo.getEmail());
		Assertions.assertEquals(2, personInfo.getMedicalRecords().getMedications().size());
		Assertions.assertEquals(1, personInfo.getMedicalRecords().getAllergies().size());
	}
}
