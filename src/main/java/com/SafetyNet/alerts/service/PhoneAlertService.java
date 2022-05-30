package com.SafetyNet.alerts.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class PhoneAlertService {
	
	@Autowired
	private FireStationRepository fireStationRepository;
	@Autowired
	private PersonRepository personRepository;

	public Set<String> listOfPhonePerNumFireStation(String stationNumber) {
		
		Set<String> fireStationAddresses = fireStationRepository.findByStation(stationNumber).stream()
				.map(fs -> fs.getAddress()).collect(Collectors.toSet());

		return personRepository.findByAddressIn(fireStationAddresses).stream()
				.map(p -> p.getPhone()).collect(Collectors.toSet());
	}
}
