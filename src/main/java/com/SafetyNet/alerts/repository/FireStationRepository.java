package com.SafetyNet.alerts.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.model.Store;

@Service
public class FireStationRepository {

	private Store store;

	@Autowired
	public FireStationRepository(Store store) {
		this.store = store;
	}

	public List<FireStation> findAll() {
		return store.getFirestations();
	}

	public List<FireStation> findByStation(String stationNumber) {

		return store.getFirestations().stream()
				.filter(s -> stationNumber.equals(s.getStation()))
				.collect(Collectors.toList());
	}

	public List<FireStation> findByAddress(String stationAddress) {

		return store.getFirestations().stream()
				.filter(s -> stationAddress.equals(s.getAddress()))
				.collect(Collectors.toList());
	}	

	public Optional<FireStation> getFirestation(String stationAddress) {
		return store.getFirestations().stream()
				.filter(s -> stationAddress.equals(s.getAddress()))
				.findFirst();
	}
	public FireStation save(FireStation firestation) {
		delete(firestation.getAddress());
		store.getFirestations().add(firestation);
		return firestation;
	}

	public void delete(String stationAddress) {
		getFirestation(stationAddress)
			.ifPresent(p -> store.getFirestations().remove(p));
	}
}


