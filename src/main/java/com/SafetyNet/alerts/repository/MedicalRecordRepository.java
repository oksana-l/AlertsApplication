package com.SafetyNet.alerts.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.model.MedicalRecord;
import com.SafetyNet.alerts.model.Store;

@Service
public class MedicalRecordRepository {
	
	@Autowired
	private Store store;
	
	public MedicalRecordRepository(Store store) {
		
		this.store = store;
	}
	
	public List<MedicalRecord> findAll() {
		
		return store.getMedicalRecord();
	}
	
	public MedicalRecord findByName(String firstName, String lastName) {

		return store.getMedicalRecord().stream()
				.filter(fn -> firstName.equals(fn.getFirstName()))
				.filter(ln -> lastName.equals(ln.getLastName()))
				.collect(Collectors.toList()).get(0);
	}
	
	public List<MedicalRecord> findByFirstName(String firstName) {

		return store.getMedicalRecord().stream()
				.filter(fn -> firstName.equals(fn.getFirstName()))
				.collect(Collectors.toList());
	}
}
