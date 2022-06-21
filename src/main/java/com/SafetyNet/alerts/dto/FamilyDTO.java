package com.SafetyNet.alerts.dto;

import java.util.List;

public class FamilyDTO {

	List<PersonFloodDTO> family;
	String address;

	public FamilyDTO() {
		
	}

	public FamilyDTO(List<PersonFloodDTO> family, String address) {
		
		this.family = family;
		this.address = address;
	}

	public List<PersonFloodDTO> getFamily() {
		return family;
	}

	public void setFamily(List<PersonFloodDTO> family) {
		this.family = family;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "FamilyDTO [family=" + family + ", \n address=" + address + "\n \n ]";
	}

}
