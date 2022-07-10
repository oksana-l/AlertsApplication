package com.SafetyNet.alerts.dto;

import java.util.List;

public class MedicalRecordsInfoDTO {
	private List<String> medications;
	private List<String> allergies;
	
	public MedicalRecordsInfoDTO() {
		
	}
	
	public MedicalRecordsInfoDTO(List<String> medications, List<String> allergies) {
		
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
