package com.SafetyNet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.service.FireStationService;

@RestController
public class FireStationController {

	@Autowired 
	private FireStationService fireStationService;
	
	@GetMapping("/firestation")
	public List<PersonDTO> personsPerStation(@RequestParam String stationNumber) {
		return fireStationService.personsPerStation(stationNumber);
	}
}
