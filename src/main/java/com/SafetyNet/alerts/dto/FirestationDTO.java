package com.SafetyNet.alerts.dto;

import java.util.List;

public class FirestationDTO {

	private List<PersonPerStationDTO> personPerStationDTO;
	private long majors;
	private long minors;
	
	public FirestationDTO() {
		
	}

	public FirestationDTO(List<PersonPerStationDTO> personPerStationDTO, long majors, long minors) {
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

	public long getMajors() {
		return majors;
	}

	public void setMajors(long majors) {
		this.majors = majors;
	}

	public long getMinors() {
		return minors;
	}

	public void setMinors(long minors) {
		this.minors = minors;
	}
}
