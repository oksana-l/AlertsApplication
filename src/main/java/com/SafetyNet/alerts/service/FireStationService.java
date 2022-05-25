package com.SafetyNet.alerts.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.FirestationDTO;
import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.dto.PersonPerStationDTO;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class FireStationService {
	
	@Autowired
	private FireStationRepository fireStationRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private MedicalRecordsService medicalRecordsService;
	
	
	// Returns a list of persons with their first and last names, their addresses and phone numbers
	public List<PersonPerStationDTO> personsPerStation(String stationNumber) {
		
		Set<String> fireStationsAddress = fireStationRepository.findByStation(stationNumber)
				.stream().map(fireStation -> fireStation.getAddress()).collect(Collectors.toSet());
		
		List<PersonPerStationDTO> personsPerStation = personRepository.findByAddressIn(fireStationsAddress)
				.stream().map(p -> new PersonPerStationDTO(p.getFirstName(), p.getLastName(), p.getAddress(), p.getPhone()))
				.collect(Collectors.toList());

		return personsPerStation;
	}
	
	// Returns a list of persons with their first and last names
	public List<PersonDTO> person(String stationNumber) {
	
		return personsPerStation(stationNumber)
				.stream().map(p -> new PersonDTO(p.getFirstName(), p.getLastName()))
				.collect(Collectors.toList());
	}
	
	/**
	 *  
	 */
	public FirestationDTO personsPerStationAndAge(String stationNumber) {
		FirestationDTO firestationDTO = new FirestationDTO();
		List<PersonPerStationDTO> personsPerStation = personsPerStation(stationNumber);
		firestationDTO.setPersonPerStationDTO(personsPerStation);
		firestationDTO.setMajors(personsPerStation.stream().filter(p -> medicalRecordsService.getAgeOfPerson(p.getFirstName(), p.getLastName()) > 18).count());
		firestationDTO.setMinors(personsPerStation.stream().filter(p -> medicalRecordsService.getAgeOfPerson(p.getFirstName(), p.getLastName()) < 18).count());
		return firestationDTO;
	}
}
