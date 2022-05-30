package com.SafetyNet.alerts.dto;

public class FirePersonDTO {

	private String firstName;
	private String lastName;
	private int age;
	private String phone;
	private MedicalRecordsInfoDTO medical;
	
	public FirePersonDTO() {
		
	}

	public FirePersonDTO(String firstName, String lastName, int age, String phone, MedicalRecordsInfoDTO medical) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phone = phone;
		this.medical = medical;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public MedicalRecordsInfoDTO getMedical() {
		return medical;
	}

	public void setMedical(MedicalRecordsInfoDTO medical) {
		this.medical = medical;
	}
}
