package com.SafetyNet.alerts.dto;

public class PersonFloodDTO {

	private String firstName;
	private String lastName;
	private String phone;
	private int age;
	private MedicalRecordsInfoDTO medicalRecords;
	
	public PersonFloodDTO() {
		
	}

	public PersonFloodDTO(String firstName, String lastName, String phone, int age, MedicalRecordsInfoDTO medicalRecords) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
		this.medicalRecords = medicalRecords;
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
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public MedicalRecordsInfoDTO getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(MedicalRecordsInfoDTO medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	
}
