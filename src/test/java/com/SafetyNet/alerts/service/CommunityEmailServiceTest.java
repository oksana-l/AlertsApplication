package com.SafetyNet.alerts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.PersonRepository;

public class CommunityEmailServiceTest {
	
	PersonRepository personRepository;
	CommunityEmailService communityEmailService;
	
	Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513","bstel@email.com");
	Person person3 = new Person("Peter", "Duncan", "1509 Culver St", "Culver", "97451", "841-874-6512", "drk@email.com");
	List<Person> persons = Arrays.asList(person1, person2, person3);
	
	@BeforeEach
	public void setUp() {
		personRepository = mock(PersonRepository.class);
		communityEmailService = new CommunityEmailService(personRepository);
	}
	
	@Test
	public void shouldListOfMailsTest() {
		when(personRepository.findAllByCity(any())).thenReturn(persons);
		List<String> listOfMails = communityEmailService.listOfMails("Culver");
		Assertions.assertEquals(3, listOfMails.size());
		Assertions.assertEquals("jaboyd@email.com", listOfMails.get(0));
		Assertions.assertEquals("bstel@email.com", listOfMails.get(1));
		Assertions.assertEquals("drk@email.com", listOfMails.get(2));
	}
}
