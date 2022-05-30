package com.SafetyNet.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.ChildAlertDTO;
import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class ChildAlertService {
	
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	MedicalRecordsService medicalRecordsService;
	
	public List<ChildAlertDTO> listOfChildPerAddress(String address) {
		
		List<ChildAlertDTO> children = new ArrayList<ChildAlertDTO>();
		
		List<Person> minorsPerAddress = personRepository.findByAddress(address).stream()
				.filter(p -> medicalRecordsService.isMinor(p)).collect(Collectors.toList());
		//System.out.println(minorsPerAddress.toString());
		
		for (Person child : minorsPerAddress) {
			ChildAlertDTO childAlertDTO = new ChildAlertDTO();
			childAlertDTO.setFirstName(child.getFirstName());
			childAlertDTO.setLastName(child.getLastName());
			childAlertDTO.setAge(medicalRecordsService.getAgeOfPerson(child.getFirstName(), child.getLastName()));
			children.add(childAlertDTO);
		}
		
		return children;
	}
	
	public List<PersonDTO> listOfAdultPerAddress(String address) {
		
		List<PersonDTO> adults= new ArrayList<PersonDTO>();
		
		List<Person> majorsPerAddress = personRepository.findByAddress(address).stream()
				.filter(p -> medicalRecordsService.isMajor(p)).collect(Collectors.toList());
		
		for (Person person : majorsPerAddress) {
			PersonDTO personDTO = new PersonDTO();
			personDTO.setFirstName(person.getFirstName());
			personDTO.setLastName(person.getLastName());
			adults.add(personDTO);
		}
		return adults;
	}

}
