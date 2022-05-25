package com.SafetyNet.alerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.MedicalRecordsInfoDTO;
import com.SafetyNet.alerts.model.MedicalRecords;
import com.SafetyNet.alerts.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordsService {

	@Autowired
	private MedicalRecordsRepository medicalRecordsRepository;
	
	public int getAgeOfPerson(String firstName, String lastName) {
		MedicalRecords medicalRecord = medicalRecordsRepository
				.findByName(firstName, lastName);
		LocalDate birthdate = LocalDate.parse(medicalRecord.getbirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		LocalDate today = LocalDate.now();
		return Period.between(birthdate, today).getYears();
	}
	
	public MedicalRecordsInfoDTO findByNameDTO(String firstName, String lastName) {
		
		MedicalRecordsInfoDTO medicalRecords = new MedicalRecordsInfoDTO();
		
		MedicalRecords medicalRecord = medicalRecordsRepository
				.findByName(firstName, lastName);
		medicalRecords.setMedications(medicalRecord.getMedications());
		medicalRecords.setAllergies(medicalRecord.getAllergies());
		return medicalRecords;				
	}
}
