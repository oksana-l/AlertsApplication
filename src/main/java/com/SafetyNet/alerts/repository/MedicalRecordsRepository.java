package com.SafetyNet.alerts.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.model.MedicalRecords;
import com.SafetyNet.alerts.model.Store;

@Service
public class MedicalRecordsRepository {
	
	@Autowired
	private Store store;
	
	public List<MedicalRecords> findAll() {
		
		return store.getMedicalrecords();
	}
	
	public MedicalRecords findByName(String firstName, String lastName) {

		return store.getMedicalrecords().stream()
				.filter(fn -> firstName.equals(fn.getFirstName()))
				.filter(ln -> lastName.equals(ln.getLastName()))
				.collect(Collectors.toList()).get(0);
	}
	
	public List<MedicalRecords> findByFirstName(String firstName) {

		return store.getMedicalrecords().stream()
				.filter(fn -> firstName.equals(fn.getFirstName()))
				.collect(Collectors.toList());
	}
	
	public String birthDate(String firstName, String lastName) {
		
		return findByName(firstName, lastName).getbirthdate();
	}
}
