package com.SafetyNet.alerts.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MedicalRecordsInfoDTO {
	private List<String> medications;
	private List<String> allergies;
	
	public MedicalRecordsInfoDTO() {
		super();
	}
	
	public MedicalRecordsInfoDTO(List<String> medications, List<String> allergies) {
		super();
		this.medications = medications;
		this.allergies = allergies;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	
}
