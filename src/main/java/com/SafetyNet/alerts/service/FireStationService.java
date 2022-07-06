package com.SafetyNet.alerts.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.FirestationDTO;
import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.dto.PersonPerStationDTO;
import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.repository.FireStationRepository;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class FireStationService {

	private FireStationRepository fireStationRepository;
	private PersonRepository personRepository;
	private MedicalRecordService medicalRecordService;

	@Autowired
	public FireStationService(FireStationRepository fireStationRepository, PersonRepository personRepository,
			MedicalRecordService medicalRecordService) {
		this.fireStationRepository = fireStationRepository;
		this.personRepository = personRepository;
		this.medicalRecordService = medicalRecordService;
	}

	// Returns a list of persons with their first and last names, their addresses and phone numbers
	public List<PersonPerStationDTO> personsPerStation(String stationNumber) {

		Set<String> fireStationsAddress = fireStationRepository.findByStation(stationNumber)
				.stream().map(fireStation -> fireStation.getAddress()).collect(Collectors.toSet());

		return personRepository.findByAddressIn(fireStationsAddress).stream()
				.map(p -> new PersonPerStationDTO(p.getFirstName(), p.getLastName(), p.getAddress(), p.getPhone()))
				.collect(Collectors.toList());
	}

	// Returns a list of persons with their first and last names
	public List<PersonDTO> personPerStation(String stationNumber) {
		return personsPerStation(stationNumber)
				.stream().map(p -> new PersonDTO(p.getFirstName(), p.getLastName()))
				.collect(Collectors.toList());
	}

	public FirestationDTO personsPerStationAndAge(String stationNumber) {
		FirestationDTO firestationDTO = new FirestationDTO();
		List<PersonPerStationDTO> personsPerStation = personsPerStation(stationNumber);
		firestationDTO.setPersonPerStationDTO(personsPerStation);
		firestationDTO.setNumberOfMajors(personsPerStation.stream() .filter(p -> medicalRecordService
				.getAgeOfPerson(p.getFirstName(), p.getLastName()) > 18).count());
		firestationDTO.setNumberOfMinors(personsPerStation.stream().filter(p -> medicalRecordService
				.getAgeOfPerson(p.getFirstName(), p.getLastName()) < 18).count());
		return firestationDTO;
	}
	
	public FireStation createFireStation(FireStation fireStationToCreate) throws Exception {
		Optional<FireStation> p = fireStationRepository
				.getFirestation(fireStationToCreate.getAddress());
		if (p.isPresent()) {
			throw new Exception("Firestation alredy exists");
		}
		return fireStationRepository.save(fireStationToCreate);

	}

	public Optional<FireStation> updateFireStation(String stationAddress, 
			FireStation fireStationToUpdate) {
		
		return fireStationRepository.getFirestation(stationAddress).map(firestation -> {

			firestation.setAddress(fireStationToUpdate.getAddress());

			firestation.setStation(fireStationToUpdate.getStation());

			return fireStationRepository.save(firestation);
		});
	}

	public void deleteFireStation(String address) {
		fireStationRepository.delete(address);
	}
}