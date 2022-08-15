package com.SafetyNet.alerts.dto;

import java.util.List;

public class ChildAlertDTO {

	private String firstName;
	private String lastName;
	private int age;
	private List<PersonDTO> listOfAdult;
	
	public ChildAlertDTO() {
		
	}

	public ChildAlertDTO(String firstName, String lastName, int age, List<PersonDTO> listOfAdult) {	
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.listOfAdult = listOfAdult;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<PersonDTO> getListOfAdult() {
		return listOfAdult;
	}

	public void setListOfAdult(List<PersonDTO> listOfAdult) {
		this.listOfAdult = listOfAdult;
	}

}
