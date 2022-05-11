package com.SafetyNet.alerts.model;

import java.util.List;

public class Store {
	private List<Person> persons;
	private List<MedicalRecords> medicalrecords;
	private List<FireStation> firestations;
	
	public Store() {
		super();
	}

	public Store(List<Person> persons, List<MedicalRecords> medicalrecords, List<FireStation> firestations) {
		super();
		this.persons = persons;
		this.medicalrecords = medicalrecords;
		this.firestations = firestations;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<MedicalRecords> getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}

	public List<FireStation> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<FireStation> firestations) {
		this.firestations = firestations;
	}

	@Override
	public String toString() {
		return "Store :\n [persons=" + persons + ",\n medicalrecords=" + medicalrecords + ",\n firestations=" + firestations
				+ "]";
	}
	
	
}
