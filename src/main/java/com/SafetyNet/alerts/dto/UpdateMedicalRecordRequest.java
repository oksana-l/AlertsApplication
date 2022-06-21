package com.SafetyNet.alerts.dto;

import java.util.List;

public class UpdateMedicalRecordRequest {

	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
	
	public UpdateMedicalRecordRequest() {
		
	}

	public UpdateMedicalRecordRequest(String birthdate, List<String> medications, List<String> allergies) {
		
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
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
