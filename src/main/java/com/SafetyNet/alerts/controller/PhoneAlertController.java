package com.SafetyNet.alerts.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.service.PhoneAlertService;

@RestController
public class PhoneAlertController {

	public PhoneAlertController(PhoneAlertService phoneAlertService) {
		
		this.phoneAlertService = phoneAlertService;
	}

	@Autowired
	private PhoneAlertService phoneAlertService;
	
	@GetMapping("/phoneAlert") 
	public Set<String> listOfPhonePerFireStation(@RequestParam String firestation) {
		return phoneAlertService.listOfPhonePerNumFireStation(firestation);
	}
}
