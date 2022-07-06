package com.SafetyNet.alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.dto.FirestationDTO;
import com.SafetyNet.alerts.model.FireStation;
import com.SafetyNet.alerts.service.FireStationService;

@RestController
public class FireStationController {

	@Autowired
	private FireStationService fireStationService;

	public FireStationController(FireStationService fireStationService) {
		this.fireStationService = fireStationService;
	}

	@GetMapping("/firestation")
	public FirestationDTO personsPerStation(@RequestParam String stationNumber) {
		return fireStationService.personsPerStationAndAge(stationNumber);
	}

	@PostMapping("/firestation")
	public ResponseEntity<FireStation> createFireStation(@RequestBody FireStation firestation) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(fireStationService.createFireStation(firestation)); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PutMapping("/firestation/{address}")
	public ResponseEntity<FireStation> updateFireStation(@PathVariable("address") String stationAddress,
			@RequestBody FireStation firestation) {
		return fireStationService.updateFireStation(stationAddress, firestation).map(mr -> ResponseEntity.ok(mr))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/firestation/{address}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFireStation(@PathVariable("address") final String address) {
		fireStationService.deleteFireStation(address);
	}
}