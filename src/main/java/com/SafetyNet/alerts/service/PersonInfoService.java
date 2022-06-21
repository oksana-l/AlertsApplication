package com.SafetyNet.alerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.PersonInfoDTO;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class PersonInfoService {

	private MedicalRecordService medicalRecordService;
	private PersonRepository personRepository;

	@Autowired
	public PersonInfoService(MedicalRecordService medicalRecordService, PersonRepository personRepository) {
		this.medicalRecordService = medicalRecordService;
		this.personRepository = personRepository;
	}

	public PersonInfoDTO personInfo(String firstName, String lastName) {
		PersonInfoDTO personInfo = new PersonInfoDTO();
		personRepository.findByFirstNameAndLastName(firstName, lastName).ifPresent(person -> {
			personInfo.setAddress(person.getAddress());
			personInfo.setEmail(person.getEmail());
		});
		personInfo.setName(firstName + " " + lastName);
		personInfo.setAge(medicalRecordService.getAgeOfPerson(firstName, lastName));
		personInfo.setMedicalRecords(medicalRecordService
				.findByNameDTO(firstName, lastName));
		return personInfo;
	}
}
