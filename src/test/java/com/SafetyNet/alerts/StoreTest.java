package com.SafetyNet.alerts;

import java.util.Arrays;
import java.util.List;

import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.model.MedicalRecord;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.model.Store;

public class StoreTest {

	public static Store testStore() {
		
		Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513","drk@email.com");
		Person person3 = new Person("Peter", "Duncan", "644 Gershwin Cir", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		List<Person> persons = Arrays.asList(person1, person2, person3);
		
		MedicalRecord medicalRecord1 = new MedicalRecord("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));
		MedicalRecord medicalRecord2 = new MedicalRecord("Jacob", "Boyd", "03/06/1989", Arrays.asList("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), Arrays.asList());
		MedicalRecord medicalRecord3 = new MedicalRecord("Peter", "Duncan", "09/06/2000", Arrays.asList(), Arrays.asList("shellfish"));
		List<MedicalRecord> medicalRecords = Arrays.asList(medicalRecord1, medicalRecord2, medicalRecord3);
		
		FireStation firestation1 = new FireStation("1509 Culver St", "3");
		FireStation firestation2 = new FireStation("834 Binoc Ave", "2");
		FireStation firestation3 = new FireStation("947 E. Rose Dr", "1");
		List<FireStation> firestations = Arrays.asList(firestation1, firestation2, firestation3);
		
		Store newStore = new Store();
		newStore.setPersons(persons);
		newStore.setMedicalrecords(medicalRecords);
		newStore.setFirestations(firestations);
		
		return newStore;
	}
	
}
