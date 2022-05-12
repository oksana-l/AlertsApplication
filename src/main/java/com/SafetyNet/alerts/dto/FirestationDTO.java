package com.SafetyNet.alerts.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FirestationDTO {

	private List<PersonPerStationDTO> personPerStationDTO;
	private int majors;
	private int minors;
	
	public FirestationDTO() {
		
	}

	public FirestationDTO(List<PersonPerStationDTO> personPerStationDTO, int majors, int minors) {
		super();
		this.personPerStationDTO = personPerStationDTO;
		this.majors = majors;
		this.minors = minors;
	}

	public List<PersonPerStationDTO> getPersonPerStationDTO() {
		return personPerStationDTO;
	}

	public void setPersonPerStationDTO(List<PersonPerStationDTO> personPerStationDTO) {
		this.personPerStationDTO = personPerStationDTO;
	}

	public int getMajors() {
		return majors;
	}

	public void setMajors(int majors) {
		this.majors = majors;
	}

	public int getMinors() {
		return minors;
	}

	public void setMinors(int minors) {
		this.minors = minors;
	}
}
