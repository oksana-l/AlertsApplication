package com.SafetyNet.alerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.MedicalRecordsInfoDTO;
import com.SafetyNet.alerts.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordsService {

	@Autowired
	private MedicalRecordsRepository medicalRecordsRepository;
	@Autowired
	private MedicalRecordsInfoDTO medicalRecords;
	
	public int ageOfPerson(String firstName, String lastName) {
		String date = medicalRecordsRepository.birthDate(firstName, lastName);
		LocalDate birthdate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		LocalDate today = LocalDate.now();
		return Period.between(birthdate, today).getYears();
	}
	
	public boolean isMajor(String firstName, String lastName) {
		return ageOfPerson(firstName, lastName) > 18;
	}
	
	public boolean isMinor(String firstName, String lastName) {
		return ageOfPerson(firstName, lastName) < 18;
	}
	
	public MedicalRecordsInfoDTO medicalRecords(String firstName, String lastName) {
		medicalRecords.setAllergies(medicalRecordsRepository
				.findByName(firstName, lastName).getAllergies());
		medicalRecords.setMedications(medicalRecordsRepository
				.findByName(firstName, lastName).getMedications());
		return medicalRecords;
	}
}
