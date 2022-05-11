package com.SafetyNet.alerts.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class FireStationService {
	
	@Autowired
	private FireStationRepository fireStationRepository;
	@Autowired
	private PersonRepository personRepository;
	
	public List<PersonDTO> personsPerStation(String stationNumber) {
		
		Set<String> fireStationsAddress = fireStationRepository.findByStation(stationNumber)
				.stream().map(fireStation -> fireStation.getAddress()).collect(Collectors.toSet());
		
		List<PersonDTO> person = personRepository.findByAddressIn(fireStationsAddress)
				.stream().map(p -> new PersonDTO(p.getFirstName(), p.getLastName(), p.getAddress(), p.getPhone()))
				.collect(Collectors.toList());

		return person;
	}
}
