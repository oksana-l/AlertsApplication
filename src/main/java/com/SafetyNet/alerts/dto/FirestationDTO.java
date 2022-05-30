package com.SafetyNet.alerts.dto;

import java.util.List;

public class FirestationDTO {

	private List<PersonPerStationDTO> personPerStationDTO;
	private long numberOfMajors;
	private long numberOfMinors;
	
	public FirestationDTO() {
		
	}

	public FirestationDTO(List<PersonPerStationDTO> personPerStationDTO, long numberOfMajors, long numberOfMinors) {
		super();
		this.personPerStationDTO = personPerStationDTO;
		this.numberOfMajors = numberOfMajors;
		this.numberOfMinors = numberOfMinors;
	}

	public List<PersonPerStationDTO> getPersonPerStationDTO() {
		return personPerStationDTO;
	}

	public void setPersonPerStationDTO(List<PersonPerStationDTO> personPerStationDTO) {
		this.personPerStationDTO = personPerStationDTO;
	}

	public long getNumberOfMajors() {
		return numberOfMajors;
	}

	public void setNumberOfMajors(long numberOfMajors) {
		this.numberOfMajors = numberOfMajors;
	}

	public long getNumberOfMinors() {
		return numberOfMinors;
	}

	public void setNumberOfMinors(long numberOfMinors) {
		this.numberOfMinors = numberOfMinors;
	}
}
