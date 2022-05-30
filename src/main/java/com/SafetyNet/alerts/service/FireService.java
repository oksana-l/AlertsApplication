package com.SafetyNet.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.FireDTO;
import com.SafetyNet.alerts.dto.FirePersonDTO;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class FireService {
	
	@Autowired
	private FireStationRepository fireStationRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private MedicalRecordsService medicalRecordsService;
	
	public List<FirePersonDTO> listOfFirePersons(String address) {
		
		List<FirePersonDTO> listOfFirePersons = new ArrayList<FirePersonDTO>();
		
		List<Person> persons = personRepository.findByAddress(address);
		
		for (Person person : persons) {
			FirePersonDTO firePerson = new FirePersonDTO();
			firePerson.setFirstName(person.getFirstName());
			firePerson.setLastName(person.getLastName());
			firePerson.setPhone(person.getPhone());
			firePerson.setAge(medicalRecordsService
					.getAgeOfPerson(person.getFirstName(), person.getLastName()));
			firePerson.setMedical(medicalRecordsService
					.findByNameDTO(person.getFirstName(), person.getLastName()));
			listOfFirePersons.add(firePerson);
		}
		return listOfFirePersons;
	}

	public FireDTO listOfDataFire(String address) {

		FireDTO listOfDataFire = new FireDTO();
		listOfDataFire.setListOfFirePerson(listOfFirePersons(address));
		listOfDataFire.setNumberFireStation(fireStationRepository.findByAddress(address)
				.stream().map(fs -> fs.getStation()).collect(Collectors.toList()));
		
		return listOfDataFire;
	}
}
