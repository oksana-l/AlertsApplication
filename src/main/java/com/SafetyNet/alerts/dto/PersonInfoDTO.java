package com.SafetyNet.alerts.dto;

public class PersonInfoDTO {

	private String name;
	private String address;
	private int age;
	private String email;
	private MedicalRecordsInfoDTO medicalRecords;
	
	public PersonInfoDTO() {
		super();
	}
	
	public PersonInfoDTO(String name, String address, int age, String email, MedicalRecordsInfoDTO medicalRecords) {
		super();
		this.name = name;
		this.address = address;
		this.age = age;
		this.email = email;
		this.medicalRecords = medicalRecords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MedicalRecordsInfoDTO getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(MedicalRecordsInfoDTO medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	
	
}
