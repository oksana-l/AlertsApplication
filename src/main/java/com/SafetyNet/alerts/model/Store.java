package com.SafetyNet.alerts.model;

import java.util.ArrayList;
import java.util.List;

public class Store {
	private List<Person> persons = new ArrayList<>();
	private List<MedicalRecord> medicalrecords = new ArrayList<>();
	private List<FireStation> firestations = new ArrayList<>();
	


	public Store() {
		
	}
	
	
	public List<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(List<Person> persons) {
		this.persons = new ArrayList<>(persons);
	}

	public List<MedicalRecord> getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
		this.medicalrecords = new ArrayList<>(medicalrecords);
	}

	public List<FireStation> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<FireStation> firestations) {
		this.firestations = new ArrayList<>(firestations);
	}
	
}
