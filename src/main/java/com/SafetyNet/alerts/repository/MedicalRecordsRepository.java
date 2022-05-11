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
	
	public List<MedicalRecords> findByFirstName(String firstName) {

		return store.getMedicalrecords().stream()
				.filter(fm -> firstName.equals(fm.getFirstName()))
				.collect(Collectors.toList());
	}
}
