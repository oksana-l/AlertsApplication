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
		Optional<MedicalRecord> medicalRecord = medicalRecordRepository
				.findByName(firstName, lastName);
		LocalDate birthdate = LocalDate.parse(medicalRecord.get()
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
		
		MedicalRecordsInfoDTO medicalrecords = new MedicalRecordsInfoDTO();
		
		Optional<MedicalRecord> medicalrecord = medicalRecordRepository
				.findByName(firstName, lastName);
		medicalrecords.setMedications(medicalrecord.get().getMedications());
		medicalrecords.setAllergies(medicalrecord.get().getAllergies());
		return medicalrecords;				
	}
	
	public Optional<MedicalRecord> getMedicalrecord(String name) {
		String firstName = name.split(" ")[0];
		String lastName= name.split(" ")[1];
		return medicalRecordRepository.findByName(firstName, lastName);
	}
	
	
	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecordToCreate) throws Exception {
		Optional<MedicalRecord> mr = medicalRecordRepository
				.findByName(medicalRecordToCreate.getFirstName(), medicalRecordToCreate.getLastName());
		if (mr.isPresent()) {
			throw new Exception("Medicalrecord already exists");
		}
		return medicalRecordRepository.save(medicalRecordToCreate);
	}
	
	public Optional<MedicalRecord> updateMedicalRecord(String name, 
			UpdateMedicalRecordRequest medicalRecordToUpdate) {
		return getMedicalrecord(name).map(mr -> {
			
			mr.setbirthdate(medicalRecordToUpdate.getBirthdate());
			
			mr.setMedications(medicalRecordToUpdate.getMedications());

			mr.setAllergies(medicalRecordToUpdate.getAllergies());

			return medicalRecordRepository.save(mr);
		});
	}
	
	public void deleteMedicalRecord(String name) {
		String firstName = name.split(" ")[0];
		String lastName= name.split(" ")[1];
		medicalRecordRepository.delete(firstName,lastName);
	}
}
