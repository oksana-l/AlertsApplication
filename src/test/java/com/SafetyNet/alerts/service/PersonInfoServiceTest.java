package com.SafetyNet.alerts.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.dto.PersonInfoDTO;
import com.SafetyNet.alerts.model.MedicalRecords;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.model.Store;
import com.SafetyNet.alerts.repository.PersonRepository;

public class PersonInfoServiceTest {

	Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513","drk@email.com");
	Person person3 = new Person("Peter", "Duncan", "644 Gershwin Cir", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	List<Person> persons = Arrays.asList(person1, person2, person3);
	
	MedicalRecords medicalRecords1 = new MedicalRecords("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));
	MedicalRecords medicalRecords2 = new MedicalRecords("Jacob", "Boyd", "03/06/1989", Arrays.asList("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), Arrays.asList());
	MedicalRecords medicalRecords3 = new MedicalRecords("Peter", "Duncan", "09/06/2000", Arrays.asList(), Arrays.asList("shellfish"));
	List<MedicalRecords> medicalRecords = Arrays.asList(medicalRecords1, medicalRecords2, medicalRecords3);
	
	Store store = new Store(persons, medicalRecords, null);
	PersonRepository personRepository = new PersonRepository(store);
	
	@Test
	public void shouldPersonInfoTest() {
		PersonInfoService personInfoService = new PersonInfoService();
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
