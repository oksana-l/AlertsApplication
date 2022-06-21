package com.SafetyNet.alerts.model;

import java.util.List;

public class Store {
	private List<Person> persons;
	private List<MedicalRecord> medicalRecord;
	private List<FireStation> firestations;
	
	public Store() {
		super();
	}

	public Store(List<Person> persons, List<MedicalRecord> medicalRecord, List<FireStation> firestations) {
		super();
		this.persons = persons;
		this.medicalRecord = medicalRecord;
		this.firestations = firestations;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<MedicalRecord> getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(List<MedicalRecord> medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public List<FireStation> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<FireStation> firestations) {
		this.firestations = firestations;
	}

	@Override
	public String toString() {
		return "Store :\n [persons=" + persons + ",\n medicalRecord=" + medicalRecord + ",\n firestations=" + firestations
				+ "]";
	}
	
	
}
