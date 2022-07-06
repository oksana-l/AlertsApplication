package com.SafetyNet.alerts.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.StoreTest;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.model.Store;

public class PersonRepositoryTest {
	
	PersonRepository personRepository;
	List<Person> persons;

	@BeforeEach
	public void setUp() {
		Store testStore = StoreTest.testStore();
		personRepository = new PersonRepository(testStore);	
		persons = testStore.getPersons();
	}
	
	@Test
	public void shouldFindAllTest() {
		List<Person> findAll = personRepository.findAll();
		
		Assertions.assertEquals(persons.size(), findAll.size());
	}
	
	@Test
	public void shouldFindByFirstNameTest() {
		
		List<Person> findPerson = personRepository.findByFirstName("Peter");
		
		Assertions.assertEquals(1, findPerson.size());
		Assertions.assertEquals(persons.get(2), findPerson.get(0));
	}
	
	@Test
	public void shouldFindByFirstNameAndLastNameTest() {
		Optional<Person> findPerson = personRepository.findByFirstNameAndLastName("Jacob", "Boyd");
		
		Assertions.assertEquals(persons.get(1), findPerson.get());
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
