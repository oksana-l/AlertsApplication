package com.SafetyNet.alerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordsService {

	@Autowired
	private MedicalRecordsRepository medicalRecordsRepository;
	
	public int ageOfPerson(PersonDTO person) {
		String date = medicalRecordsRepository.birthDate(person.getFirstName(), person.getLastName());
		LocalDate birthdate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		LocalDate today = LocalDate.now();
		return Period.between(birthdate, today).getYears();
	}
	
	public boolean isMajor(PersonDTO person) {
		return ageOfPerson(person) > 18;
	}
	
	public boolean isMinor(PersonDTO person) {
		return ageOfPerson(person) < 18;
	}
}
