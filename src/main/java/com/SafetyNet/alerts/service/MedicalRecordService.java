package com.SafetyNet.alerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.MedicalRecordsInfoDTO;
import com.SafetyNet.alerts.dto.UpdateMedicalRecordRequest;
import com.SafetyNet.alerts.model.MedicalRecord;
import com.SafetyNet.alerts.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

	private MedicalRecordRepository medicalRecordRepository;
	
	@Autowired
	public MedicalRecordService (MedicalRecordRepository medicalRecordRepository) {
		
		this.medicalRecordRepository = medicalRecordRepository;
	}

	public int getAgeOfPerson(String firstName, String lastName) {
		MedicalRecord medicalRecord = medicalRecordRepository
				.findByName(firstName, lastName);
		LocalDate birthdate = LocalDate.parse(medicalRecord
				.getbirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		LocalDate today = LocalDate.now();
		return Period.between(birthdate, today).getYears();
	}
	
	public boolean isMinor(String firstName, String lastName) {
		return getAgeOfPerson(firstName, lastName) < 19;
	}
	
	public boolean isMajor(String firstName, String lastName) {
		return getAgeOfPerson(firstName, lastName) > 19;
	}
	
	public MedicalRecordsInfoDTO findByNameDTO(String firstName, String lastName) {
		
		MedicalRecordsInfoDTO medicalRecords = new MedicalRecordsInfoDTO();
		
		MedicalRecord medicalRecord = medicalRecordRepository
				.findByName(firstName, lastName);
		medicalRecords.setMedications(medicalRecord.getMedications());
		medicalRecords.setAllergies(medicalRecord.getAllergies());
		return medicalRecords;				
	}
	
	public Optional<MedicalRecord> getMedicalRecord(String name) {
		String firstName = name.split(" ")[0];
		String lastName= name.split(" ")[1];
		return Optional.ofNullable(medicalRecordRepository.findByName(firstName, lastName));
	}
	
	
	public Optional<MedicalRecord> createMedicalRecord(MedicalRecord medicalRecordToCreate) {
		return getMedicalRecord(medicalRecordToCreate.getFirstName() + " " + medicalRecordToCreate.getLastName())
				.map(mr -> {
					
					mr.setFirstName(medicalRecordToCreate.getFirstName());
					
					mr.setLastName(medicalRecordToCreate.getLastName());
					
					mr.setbirthdate(medicalRecordToCreate.getbirthdate());
					
					mr.setMedications(medicalRecordToCreate.getMedications());

					mr.setAllergies(medicalRecordToCreate.getAllergies());
					
					return mr;
				});
	}
	
	public Optional<MedicalRecord> updateMedicalRecord(String name, UpdateMedicalRecordRequest medicalRecordToUpdate) {
		return getMedicalRecord(name).map(mr -> {

			
			mr.setbirthdate(medicalRecordToUpdate.getBirthdate());
			
			mr.setMedications(medicalRecordToUpdate.getMedications());

			mr.setAllergies(medicalRecordToUpdate.getAllergies());

			return mr;
		});
	}
	
	public void deleteMedicalRecord(String name) {
		getMedicalRecord(name);
		Optional.ofNullable(null);
	}
}
