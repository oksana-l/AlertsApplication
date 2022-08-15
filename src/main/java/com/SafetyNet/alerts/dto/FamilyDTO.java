package com.SafetyNet.alerts.dto;

import java.util.List;

public class FamilyDTO {

	List<FloodDTO> family;
	String address;

	public FamilyDTO() {
		
	}

	public FamilyDTO(List<FloodDTO> family, String address) {
		
		this.family = family;
		this.address = address;
	}

	public List<FloodDTO> getFamily() {
		return family;
	}

	public void setFamily(List<FloodDTO> family) {
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
