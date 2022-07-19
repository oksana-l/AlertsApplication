package com.SafetyNet.alerts.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.StoreTest;
import com.SafetyNet.alerts.model.Store;
import com.SafetyNet.alerts.repository.PersonRepository;

public class CommunityEmailServiceTest {
	
	PersonRepository personRepository;
	CommunityEmailService communityEmailService;
	
	@BeforeEach
	public void setUp() {
		Store testStore = StoreTest.testStore();
		personRepository = new PersonRepository(testStore);
		communityEmailService = new CommunityEmailService(personRepository);
	}
	
	@Test
	public void shouldListOfMailsTest() {
		List<String> listOfMails = communityEmailService.listOfMails("Culver");
		Assertions.assertEquals(3, listOfMails.size());
		Assertions.assertEquals("jaboyd@email.com", listOfMails.get(0));
		Assertions.assertEquals("bstel@email.com", listOfMails.get(1));
		Assertions.assertEquals("drk@email.com", listOfMails.get(2));
	}
}
