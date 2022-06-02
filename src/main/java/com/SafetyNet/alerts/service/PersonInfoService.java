package com.SafetyNet.alerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.PersonInfoDTO;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class PersonInfoService {

	@Autowired
	private MedicalRecordsService medicalRecordsService;
	@Autowired
	private PersonRepository personRepository;

	public PersonInfoService(MedicalRecordsService medicalRecordsService, PersonRepository personRepository) {
		super();
		this.medicalRecordsService = medicalRecordsService;
		this.personRepository = personRepository;
	}

	public PersonInfoDTO personInfo(String firstName, String lastName) {
		PersonInfoDTO personInfo = new PersonInfoDTO();
		Person person = personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
		personInfo.setName(firstName + " " + lastName);
		personInfo.setAddress(person.getAddress());
		personInfo.setAge(medicalRecordsService.getAgeOfPerson(firstName, lastName));
		personInfo.setEmail(person.getEmail());
		personInfo.setMedicalRecords(medicalRecordsService
				.findByNameDTO(firstName, lastName));
		return personInfo;
	}
}
