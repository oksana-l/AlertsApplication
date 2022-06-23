package com.SafetyNet.alerts.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.model.Store;

public class FireStationRepositoryTest {

	@Test
	public void shouldFindByFirstNameTest() {
		Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513","drk@email.com");
		Person person3 = new Person("Peter", "Duncan", "644 Gershwin Cir", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		List<Person> persons = new ArrayList<Person>(Arrays.asList(person1, person2, person3));
		Store store = new Store();
		PersonRepository personRepository = new PersonRepository();
		List<Person> findPerson = personRepository.findByFirstName("Peter");
		
		Assertions.assertEquals(1, findPerson.size());
		Assertions.assertEquals(person3, findPerson.get(0));
	}
}
