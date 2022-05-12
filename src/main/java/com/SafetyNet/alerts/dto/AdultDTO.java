package com.SafetyNet.alerts.dto;

public class AdultDTO {

	private String firstName;
	private String lastName;
	private String age;
	
	
	public AdultDTO() {
		
	}
	
	public AdultDTO(String firstName, String lastName, String birthdate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = birthdate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return age;
	}

	public void setBirthdate(String birthdate) {
		this.age = birthdate;
	}
	
}
