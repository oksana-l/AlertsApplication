package com.SafetyNet.alerts.dto;

import java.util.List;

public class FireDTO {

	List<FirePersonDTO> listOfFirePerson;
	List<String> numberFireStation;
	
	public FireDTO() {
		
	}

	public FireDTO(List<FirePersonDTO> listOfFirePerson, List<String> numberFireStation) {
		super();
		this.listOfFirePerson = listOfFirePerson;
		this.numberFireStation = numberFireStation;
	}

	public List<FirePersonDTO> getListOfFirePerson() {
		return listOfFirePerson;
	}

	public void setListOfFirePerson(List<FirePersonDTO> listOfFirePerson) {
		this.listOfFirePerson = listOfFirePerson;
	}

	public List<String> getNumberFireStation() {
		return numberFireStation;
	}

	public void setNumberFireStation(List<String> numberFireStation) {
		this.numberFireStation = numberFireStation;
	}
}
