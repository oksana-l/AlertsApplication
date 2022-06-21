package com.SafetyNet.alerts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.ChildAlertDTO;
import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class ChildAlertService {
	
	private PersonRepository personRepository;
	private MedicalRecordService medicalRecordService;
	
	@Autowired
	public ChildAlertService(PersonRepository personRepository, MedicalRecordService medicalRecordService) {
		
		this.personRepository = personRepository;
		this.medicalRecordService = medicalRecordService;
	}

	public List<ChildAlertDTO> listOfChildPerAddress(String address) {
		
		return personRepository.findByAddress(address).stream()
				.filter(p -> medicalRecordService.isMinor(p.getFirstName(), p.getLastName()))
				.map(child -> {
					ChildAlertDTO childAlertDTO = new ChildAlertDTO();
					childAlertDTO.setFirstName(child.getFirstName());
					childAlertDTO.setLastName(child.getLastName());
					childAlertDTO.setAge(medicalRecordService
							.getAgeOfPerson(child.getFirstName(), child.getLastName()));
					return childAlertDTO;
				})
				.collect(Collectors.toList());
	}
	
	public List<PersonDTO> listOfAdultPerAddress(String address) {
		
		return personRepository.findByAddress(address).stream()
				.filter(p -> medicalRecordService.isMajor(p.getFirstName(), p.getLastName()))
				.map(adult -> {
					PersonDTO personDTO = new PersonDTO();
					personDTO.setFirstName(adult.getFirstName());
					personDTO.setLastName(adult.getLastName());	
					return personDTO;
				})
				.collect(Collectors.toList());
	}
}
