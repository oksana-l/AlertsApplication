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
import org.mockito.AdditionalAnswers;

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
	}
	
	@Test
	public void shouldGetPersonTest() {
		when(personRepository.findByFirstNameAndLastName("John", "Boyd"))
			.thenReturn(Optional.of(person));
		
		Assertions.assertEquals(Optional.of(person), personService.getPerson("John Boyd"));
	}
	
	@Test
	public void shouldExceptionCreatePersonTest() {
		when(personRepository.findByFirstNameAndLastName("John", "Boyd"))
			.thenReturn(Optional.of(person));
		
		Assertions.assertThrows(Exception.class, () -> personService.createPerson(person));
	}
	
	@Test
	public void shouldCreatePersonTest() throws Exception {
		when(personRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
		
		Person personToCreate = new Person("Thomas", "Pasquier", "644 Gershwin Cir", "Denver",
				"98651", "841-874-3612", "drk@email.com");
		Person personSave = personService.createPerson(personToCreate);
		
		verify(personRepository, times(1)).save(personToCreate);
		Assertions.assertEquals("Thomas", personSave.getFirstName());
		Assertions.assertEquals("Thomas", personSave.getFirstName());
		Assertions.assertEquals("Thomas", personSave.getFirstName());
		Assertions.assertEquals("Thomas", personSave.getFirstName());
		Assertions.assertEquals("Thomas", personSave.getFirstName());
		Assertions.assertEquals("Thomas", personSave.getFirstName());
		Assertions.assertEquals("Thomas", personSave.getFirstName());
	}
	
	@Test
	public void shouldUpdatePersonTest() {
		when(personRepository.findByFirstNameAndLastName("John", "Boyd"))
			.thenReturn(Optional.of(person));
		when(personRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
		
		UpdatePersonRequest personToUpdate = new UpdatePersonRequest("644 Gershwin Cir", "Denver",
				"98651", "841-874-3612", "drk@email.com");
		Optional<Person> personUpdate = personService.updatePerson("John Boyd", personToUpdate);
		
		Assertions.assertEquals("644 Gershwin Cir", personUpdate.get().getAddress());
		Assertions.assertEquals("Denver", personUpdate.get().getCity());
		Assertions.assertEquals("98651", personUpdate.get().getZip());
		Assertions.assertEquals("841-874-3612", personUpdate.get().getPhone());
		Assertions.assertEquals("drk@email.com", personUpdate.get().getEmail());
	}
	
	@Test
	public void shouldDeletePersonTest() {
		personService.deletePerson("John Boyd");
		verify(personRepository, times(1)).delete("John", "Boyd");		
	}
}
