package com.SafetyNet.alerts.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.MedicalRecordsInfoDTO;

@Service
public class MedicalRecordsInfoRepository {
	
	@Autowired
	private MedicalRecordsRepository medicalRecordsRepository;
	private MedicalRecordsInfoDTO medicalRecords;

	public MedicalRecordsInfoDTO findByNameDTO(String firstName, String lastName) {
		medicalRecords.setMedications(medicalRecordsRepository
				.findByName(firstName, lastName).getMedications());
		medicalRecords.setAllergies(medicalRecordsRepository
				.findByName(firstName, lastName).getAllergies());
		return medicalRecords;				
	}
}
