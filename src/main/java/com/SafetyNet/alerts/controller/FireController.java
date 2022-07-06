package com.SafetyNet.alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.dto.FireDTO;
import com.SafetyNet.alerts.service.FireService;

@RestController
public class FireController {
	
	private FireService dataFireService;

	@Autowired
	public FireController(FireService dataFireService) {
		
		this.dataFireService = dataFireService;
	}
	
	@GetMapping("/fire")
	public FireDTO listOfDataFire(@RequestParam String address) {
		return dataFireService.listOfDataFire(address);
	}
}
