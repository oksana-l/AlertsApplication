package com.SafetyNet.alerts.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.model.Store;

public class PersonRepositoryTest {
	
	PersonRepository personRepository;
	List<Person> persons;

	@BeforeEach
	public void setUp() {
		
		Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513","drk@email.com");
		Person person3 = new Person("Peter", "Duncan", "644 Gershwin Cir", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		persons = new ArrayList(Arrays.asList(person1, person2, person3));
		Store store = new Store(persons, null, null);
		personRepository = new PersonRepository(store);	
	}
	
	@Test
	public void shouldFindAllTest() {
		List<Person> findAll = personRepository.findAll();
		
		Assertions.assertEquals(3, findAll.size());
	}
	
	@Test
	public void shouldFindByFirstNameTest() {
		
		List<Person> findPerson = personRepository.findByFirstName("Peter");
		
		Assertions.assertEquals(1, findPerson.size());
		Assertions.assertEquals(persons.get(2), findPerson.get(0));
	}
	
	@Test
	public void shouldFindByFirstNameAndLastNameTest() {
		List<Person> findPerson = personRepository.findByFirstNameAndLastName("Jacob", "Boyd");
		
		Assertions.assertEquals(persons.get(1), findPerson.get(0));
	}
	
	@Test
	public void shouldFindByAddressInTest() {
		Set<String> addresses = new HashSet<String>();
		addresses.add("1509 Culver St");
		List<Person> findPersons = personRepository.findByAddressIn(addresses);
		
		Assertions.assertEquals(2, findPersons.size());
	}
	
	@Test
	public void shouldFindAllByCity() {
		List<Person> findPersonsByCity = personRepository.findAllByCity("Culver");
		
		Assertions.assertEquals(3, findPersonsByCity.size());
	}
}
