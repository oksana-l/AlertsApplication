package com.SafetyNet.alerts.repository;

import java.util.List;
import java.util.Optional;

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
		List<FireStation> firestationsTest = firestationRepository.findAll();
		Optional<FireStation> firestation = firestationRepository.getFirestation("1509 Culver St");
		
		Assertions.assertTrue(firestationsTest.size() == 3);
		Assertions.assertEquals(firestation, Optional.of(firestationsTest.get(0)));
	}

	@Test 
	public void shouldFindByStationTest() {
		List<FireStation> firestationsTest = firestationRepository.findByStation("1");
		
		Assertions.assertTrue(firestationsTest.size() == 1);
		Assertions.assertEquals("1", firestationsTest.get(0).getStation());
	}

	@Test 
	public void shouldFindByAddressTest() {
		List<FireStation> firestationsTest = firestationRepository.findByAddress("947 E. Rose Dr");
		
		Assertions.assertTrue(firestationsTest.size() == 1);
		Assertions.assertEquals("947 E. Rose Dr", firestationsTest.get(0).getAddress());
	}

	@Test
	public void shouldGetFirestationTest() {
		Optional<FireStation> firestation = firestationRepository.getFirestation("834 Binoc Ave");
		
		Assertions.assertEquals("2", firestation.get().getStation());
	}

	@Test
	public void shouldSaveTest() {
		FireStation firestationToSave = new FireStation("146 Liberty St", "4");
		firestationRepository.save(firestationToSave);
		
		Assertions.assertTrue(firestations.size() == 4);
		Assertions.assertEquals("4", firestations.get(3).getStation());
		Assertions.assertEquals("146 Liberty St", firestations.get(3).getAddress());
	}

	@Test
	public void shouldDeleteTest() {
		firestationRepository.delete("947 E. Rose Dr");
		
		Assertions.assertTrue(firestations.size() == 2);
	}
}
