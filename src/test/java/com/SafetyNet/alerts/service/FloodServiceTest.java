package com.SafetyNet.alerts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.dto.FamilyDTO;
import com.SafetyNet.alerts.dto.MedicalRecordsInfoDTO;
import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

public class FloodServiceTest {

	FireStationRepository fireStationRepository;
	PersonRepository personRepository;
	MedicalRecordService medicalRecordService;
	FloodService floodService;
	
	Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	List<Person> persons = Arrays.asList(person);
	
	FireStation firestation = new FireStation("1509 Culver St", "1");
	List<FireStation> firestations = Arrays.asList(firestation);
	
	MedicalRecordsInfoDTO medicalRecordsInfo = new MedicalRecordsInfoDTO(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), 
			Arrays.asList("nillacilan"));
	
	@BeforeEach
	public void setUp() {
		fireStationRepository = mock(FireStationRepository.class);
		personRepository = mock(PersonRepository.class);
		medicalRecordService = mock(MedicalRecordService.class);
		floodService = new FloodService(fireStationRepository, 
				personRepository, medicalRecordService);
	}
	
	@Test
	public void shouldListOfFamilyTest() {
		when(fireStationRepository.findByStation(any())).thenReturn(firestations);
		when(medicalRecordService.getAgeOfPerson(any(), any())).thenReturn(38);
		when(personRepository.findByAddress(any())).thenReturn(persons);
		when(medicalRecordService.medicalrecordFindByFirstNameAndLastName(any(), any()))
			.thenReturn(medicalRecordsInfo);

		List<FamilyDTO> listOfFamilies = floodService.listOfFamily(Arrays.asList("1"));
		
		Assertions.assertEquals(1, listOfFamilies.size());
		Assertions.assertEquals(1,  listOfFamilies.get(0).getFamily().size());
		Assertions.assertEquals("John", listOfFamilies.get(0).getFamily().get(0).getFirstName());
		Assertions.assertEquals("Boyd", listOfFamilies.get(0).getFamily().get(0).getLastName());
		Assertions.assertEquals(38, listOfFamilies.get(0).getFamily().get(0).getAge());
		Assertions.assertEquals(2, listOfFamilies.get(0).getFamily().get(0).getMedicalRecords().getMedications().size());
		Assertions.assertEquals(1, listOfFamilies.get(0).getFamily().get(0).getMedicalRecords().getAllergies().size());
	}

}
