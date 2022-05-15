package com.SafetyNet.alerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.PersonInfoDTO;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class PersonInfoService {

	@Autowired
	private PersonInfoDTO personInfoDTO;
	@Autowired
	private MedicalRecordsService medicalRecordsService;
	@Autowired
	private PersonRepository personRepository;
	
	public String name(String firstName, String lastName) {
		
		return firstName + " " + lastName;
	}

	public PersonInfoDTO personInfo(String firstName, String lastName) {
		personInfoDTO.setName(name(firstName, lastName));
		personInfoDTO.setAddress(personRepository
				.findByFirstNameAndLastName(firstName, lastName).get(0).getAddress());
		personInfoDTO.setAge(medicalRecordsService.ageOfPerson(firstName, lastName));
		personInfoDTO.setEmail(personRepository
				.findByFirstNameAndLastName(firstName, lastName).get(0).getEmail());
		personInfoDTO.setMedicalRecordsInfoDTO(medicalRecordsService
				.medicalRecords(firstName, lastName));
		return personInfoDTO;
	}
}
