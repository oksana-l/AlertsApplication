package com.SafetyNet.alerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.PersonInfoDTO;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class PersonInfoService {

	private MedicalRecordService medicalrecordsService;
	private PersonRepository personRepository;

	@Autowired
	public PersonInfoService(MedicalRecordService medicalrecordsService, PersonRepository personRepository) {
		this.medicalrecordsService = medicalrecordsService;
		this.personRepository = personRepository;
	}

	/**
	 * @return the name, address, age, email address and medical history
	 * 		  (medication, dosage, allergies) of each resident
	 */
	public PersonInfoDTO personInfo(String firstName, String lastName) {
		PersonInfoDTO personInfo = new PersonInfoDTO();
		personRepository.findByFirstNameAndLastName(firstName, lastName).ifPresent(person -> {
			personInfo.setAddress(person.getAddress());
			personInfo.setEmail(person.getEmail());
		});
		personInfo.setName(firstName + " " + lastName);
		personInfo.setAge(medicalrecordsService.getAgeOfPerson(firstName, lastName));
		personInfo.setMedicalRecords(medicalrecordsService
				.medicalrecordFindByName(firstName, lastName)); // verifier le nom de la methode
		return personInfo;
	}
}
