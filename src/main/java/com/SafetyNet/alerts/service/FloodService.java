package com.SafetyNet.alerts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.FamilyDTO;
import com.SafetyNet.alerts.dto.PersonFloodDTO;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class FloodService {
	
	private FireStationRepository fireStationRepository;
	private PersonRepository personRepository;
	private MedicalRecordService medicalRecordService;
	
	@Autowired
	public FloodService(FireStationRepository fireStationRepository, PersonRepository personRepository,
			MedicalRecordService medicalRecordService) {
		
		this.fireStationRepository = fireStationRepository;
		this.personRepository = personRepository;
		this.medicalRecordService = medicalRecordService;
	}
	
	public List<FamilyDTO> listOfFamily(List<String> stationNumbers) {

		return stationNumbers.stream()
			.flatMap(stationNumber -> fireStationRepository.findByStation(stationNumber).stream())
			.map(fireStation -> fireStation.getAddress())
			.distinct()
			.map(this::getFamilyFromAddress)
			.collect(Collectors.toList());
	}	
	
	private FamilyDTO getFamilyFromAddress(String address) {
		
		FamilyDTO family = new FamilyDTO();
		family.setAddress(address);
		family.setFamily(personRepository.findByAddress(address).stream()
				.map(p -> {
					PersonFloodDTO personFlood = new PersonFloodDTO();
					personFlood.setFirstName(p.getFirstName());
					personFlood.setLastName(p.getLastName());
					personFlood.setAge(medicalRecordService
							.getAgeOfPerson(p.getFirstName(), p.getLastName()));
					personFlood.setMedicalRecords(medicalRecordService
							.medicalrecordFindByFirstNameAndLastName(p.getFirstName(), p.getLastName()));
					return personFlood;
			}) 
				.collect(Collectors.toList()));
		return family;
	}
}