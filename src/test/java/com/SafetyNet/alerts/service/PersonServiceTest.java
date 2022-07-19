package com.SafetyNet.alerts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.dto.UpdatePersonRequest;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.PersonRepository;

public class PersonServiceTest {

	PersonRepository personRepository;
	PersonService personService;
	Person person;
	
	@BeforeEach
	public void setUp() {
		personRepository = mock(PersonRepository.class);
		personService = new PersonService(personRepository);
		person = new Person("John", "Boyd", "1509 Culver St", "Culver", 
			"97451", "841-874-6512", "jaboyd@email.com");
		when(personRepository.findByFirstNameAndLastName(any(String.class), any(String.class)))
			.thenReturn(Optional.of(person));
	}
	
	@Test
	public void shouldGetPersonTest() {
		Assertions.assertEquals(Optional.of(person), personService.getPerson("John Boyd"));
	}
	
	@Test
	public void shouldExceptionCreatePersonTest() {
		Assertions.assertThrows(Exception.class, () -> personService.createPerson(person));
	}
	
	@Test
	public void shouldCreatePersonTest() throws Exception {
		Person personToCreate = new Person("Thomas", "Pasquier", "644 Gershwin Cir", "Denver",
				"98651", "841-874-3612", "drk@email.com");
		personService.createPerson(personToCreate);
		verify(personRepository, times(1)).save(personToCreate);
	}
	
	@Test
	public void shouldUpdatePersonTest() {
		when(personService.getPerson("John Boyd")).thenReturn(Optional.of(person));
		UpdatePersonRequest personToUpdate = new UpdatePersonRequest("644 Gershwin Cir", "Denver",
				"98651", "841-874-3612", "drk@email.com");
		personService.updatePerson("John Boyd", personToUpdate);
		
		Assertions.assertEquals("644 Gershwin Cir", person.getAddress());
		Assertions.assertEquals("Denver", person.getCity());
		Assertions.assertEquals("98651", person.getZip());
		Assertions.assertEquals("841-874-3612", person.getPhone());
		Assertions.assertEquals("drk@email.com", person.getEmail());
	}
	
	@Test
	public void shouldDeletePersonTest() {
		personService.deletePerson("John Boyd");
		verify(personRepository, times(1)).delete("John", "Boyd");		
	}
}
