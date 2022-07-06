package com.SafetyNet.alerts.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.StoreTest;
import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.model.Store;

public class FireStationRepositoryTest {
	
	FireStationRepository firestationRepository;
	List<FireStation> firestations;
	
	@BeforeEach
	public void setUp() {
		Store testStore = StoreTest.testStore();
		firestationRepository = new FireStationRepository(testStore);	
		firestations = testStore.getFirestations();
	}

	@Test
	public void shouldFindAllTest() {
		List<FireStation> firestations = firestationRepository.findAll();
		
		Assertions.assertTrue(firestations.size() > 0);
	}
}
