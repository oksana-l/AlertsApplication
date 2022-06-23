package com.SafetyNet.alerts.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.model.MedicalRecord;
import com.SafetyNet.alerts.model.Store;

@Service
public class MedicalRecordRepository {

	@Autowired
	private Store store;

	public List<MedicalRecord> findAll() {                  
		return store.getMedicalrecords();
	}

	public Optional<MedicalRecord> findByName(String firstName, String lastName) {
		return store.getMedicalrecords().stream()
				.filter(fn -> firstName.equals(fn.getFirstName()))
				.filter(ln -> lastName.equals(ln.getLastName()))
				.findFirst();
	}

	public List<MedicalRecord> findByFirstName(String firstName) {
		return store.getMedicalrecords().stream()
				.filter(fn -> firstName.equals(fn.getFirstName()))
				.collect(Collectors.toList());
	}

	public String birthDate(String firstName, String lastName) {
		return findByName(firstName, lastName).get().getbirthdate();
	}
	
	public MedicalRecord save(MedicalRecord medicalrecords) {
		delete(medicalrecords.getFirstName(), medicalrecords.getLastName());
		store.getMedicalrecords().add(medicalrecords);
		return medicalrecords;
	}
	
	public void delete(String firstName, String lastName) {
		findByName(firstName,lastName).ifPresent(p -> store.getMedicalrecords().remove(p));
	}

}