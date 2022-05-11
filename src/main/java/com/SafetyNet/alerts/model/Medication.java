package com.SafetyNet.alerts.model;

import java.util.ArrayList;
import java.util.List;

public class Medication {
	private String medication;
	private String dosage;
	
	public Medication() {
		
	}
	
	public Medication(String medication, String dosage) {
		super();
		this.medication = medication;
		this.dosage = dosage;
	}
	
	public String getMedication() {
		return medication;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	
	public List<String> addToMedications(String medication, String dosage) {
		List<String> medications = new ArrayList<>();
		medications.add(medication + dosage);
		return medications;
	}
}
