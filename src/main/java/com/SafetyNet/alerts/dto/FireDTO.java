package com.SafetyNet.alerts.dto;

import java.util.List;
import java.util.Set;

public class FireDTO {

	List<FloodDTO> listOfPerson;
	Set<String> numberFireStation;
	
	public FireDTO() {
		
	}

	public FireDTO(List<FloodDTO> listOfPerson, Set<String> numberFireStation) {
		super();
		this.listOfPerson = listOfPerson;
		this.numberFireStation = numberFireStation;
	}

	public List<FloodDTO> getListOfPerson() {
		return listOfPerson;
	}

	public void setListOfPerson(List<FloodDTO> listOfFirePerson) {
		this.listOfPerson = listOfFirePerson;
	}

	public Set<String> getNumberFireStation() {
		return numberFireStation;
	}

	public void setNumberFireStation(Set<String> numberFireStation) {
		this.numberFireStation = numberFireStation;
	}
}
