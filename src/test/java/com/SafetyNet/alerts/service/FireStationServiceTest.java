package com.SafetyNet.alerts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

public class FireStationServiceTest {

	FireStationRepository fireStationRepository;
	PersonRepository personRepository;
	MedicalRecordService medicalRecordService;
	FireStationService fireStationService;
	FireStation firestation;
	
	@BeforeEach
	public void setUp() {
		fireStationRepository = mock(FireStationRepository.class);
		personRepository = mock(PersonRepository.class);
		medicalRecordService = mock(MedicalRecordService.class);
		fireStationService = new FireStationService(fireStationRepository, 
			personRepository, medicalRecordService);
		firestation = new FireStation("834 Binoc Ave", "2");
	}
	
	@Test
	public void shouldInfoPersonsPerStationTest() {
		
	}
	
	@Test
	public void shouldPersonPerStationTest() {
		
	}
	
	@Test
	public void shouldPersonsPerStationAndAge() {
		
	}
	
	@Test
	public void shouldCreateFireStationTest() throws Exception {
		FireStation firestationToCreate = new FireStation(); 
		when(fireStationRepository.getFirestation(any(String.class)))
			.thenReturn(Optional.of(firestation));
		fireStationService.createFireStation(firestationToCreate);
		verify(fireStationRepository, times(1)).save(firestationToCreate);
	}
	
	@Test
	public void shouldUpdateFireStation() {
		
	}
	
	@Test
	public void shouldDeleteFireStationTest() {
		doNothing().when(fireStationRepository).delete(any(String.class));
		fireStationService.deleteFireStation(firestation.getAddress());
		verify(fireStationRepository, times(1)).delete(firestation.getAddress());
	}
}
