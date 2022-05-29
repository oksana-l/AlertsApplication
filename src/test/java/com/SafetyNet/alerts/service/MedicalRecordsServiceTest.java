package com.SafetyNet.alerts.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.SafetyNet.alerts.model.MedicalRecords;

public class MedicalRecordsServiceTest {
	
	@Autowired
	private MedicalRecordsService medicalRecordsService;

	@Test
	public void shouldGetAgeOfPersonTest() {
		
		MedicalRecords medicalRecords1 = new MedicalRecords("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));
		MedicalRecords medicalRecords2 = new MedicalRecords("Jacob", "Boyd", "03/06/1989", Arrays.asList("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), Arrays.asList());
		MedicalRecords medicalRecords3 = new MedicalRecords("Peter", "Duncan", "09/06/2000", Arrays.asList(), Arrays.asList("shellfish"));
		List<MedicalRecords> medicalRecords = Arrays.asList(medicalRecords1, medicalRecords2, medicalRecords3);
		
		Assertions.assertEquals(38, medicalRecordsService.getAgeOfPerson("John", "Boyd"));
	}
}
