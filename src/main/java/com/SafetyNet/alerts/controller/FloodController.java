package com.SafetyNet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.dto.FamilyDTO;
import com.SafetyNet.alerts.service.FloodService;

@RestController
public class FloodController {

	@Autowired
	private FloodService floodService;
	
	public FloodController(FloodService floodService) {
		
		this.floodService = floodService;
	}

	@GetMapping("/flood/stations")
	public List<FamilyDTO> listOfFamily(@RequestParam List<String> stations) {
		return floodService.listOfFamily(stations);
	}
}
