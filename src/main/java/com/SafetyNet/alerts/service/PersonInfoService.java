package com.SafetyNet.alerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.PersonInfoDTO;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class PersonInfoService {

	@Autowired
	private MedicalRecordsService medicalRecordsService;
	@Autowired
	private PersonRepository personRepository;

	public PersonInfoDTO personInfo(String firstName, String lastName) {
		PersonInfoDTO personInfo = new PersonInfoDTO();
		personInfo.setName(firstName + " " + lastName);
		personInfo.setAddress(personRepository
				.findByFirstNameAndLastName(firstName, lastName).get(0).getAddress());
		personInfo.setAge(medicalRecordsService.getAgeOfPerson(firstName, lastName));
		personInfo.setEmail(personRepository
				.findByFirstNameAndLastName(firstName, lastName).get(0).getEmail());
		personInfo.setMedicalRecords(medicalRecordsService
				.findByNameDTO(firstName, lastName));
		return personInfo;
	}
}
