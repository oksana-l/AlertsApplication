package com.SafetyNet.alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.dto.FirestationDTO;
import com.SafetyNet.alerts.service.FireStationService;

@RestController
public class FireStationController {

	public FireStationController(FireStationService fireStationService) {
		
		this.fireStationService = fireStationService;
	}

	@Autowired 
	private FireStationService fireStationService;
	
	@GetMapping("/firestation")
	public FirestationDTO personsPerStation(@RequestParam String stationNumber) {
		return fireStationService.personsPerStationAndAge(stationNumber);
	}
}
