package com.SafetyNet.alerts.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.dto.PersonInfoDTO;
import com.SafetyNet.alerts.model.MedicalRecord;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.model.Store;
import com.SafetyNet.alerts.repository.PersonRepository;

public class PersonInfoServiceTest {

	Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513","drk@email.com");
	Person person3 = new Person("Peter", "Duncan", "644 Gershwin Cir", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	List<Person> persons = Arrays.asList(person1, person2, person3);
	
	MedicalRecord medicalRecord1 = new MedicalRecord("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));
	MedicalRecord medicalRecord2 = new MedicalRecord("Jacob", "Boyd", "03/06/1989", Arrays.asList("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), Arrays.asList());
	MedicalRecord medicalRecord3 = new MedicalRecord("Peter", "Duncan", "09/06/2000", Arrays.asList(), Arrays.asList("shellfish"));
	List<MedicalRecord> medicalRecord = Arrays.asList(medicalRecord1, medicalRecord2, medicalRecord3);
	
	Store store = new Store();
	PersonRepository personRepository = new PersonRepository();
	
	@Test
	public void shouldPersonInfoTest() {
		PersonInfoService personInfoService = new PersonInfoService(null, personRepository);
		PersonInfoDTO personInfo = personInfoService.personInfo(person1.getFirstName(), person1.getLastName());
		
		Assertions.assertEquals("John Boyd", personInfo.getName());
	}
	
	@Test
	public void shouldPersonTest() {
		List<Person> findAllPersons = personRepository.findAll();
		
		Assertions.assertEquals(persons, findAllPersons);
		Assertions.assertEquals(person1, findAllPersons.get(0));
		Assertions.assertEquals("John", findAllPersons.get(0).getFirstName());
	}
}
