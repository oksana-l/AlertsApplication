package com.SafetyNet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.service.PhoneAlertService;

@RestController
public class PhoneAlertController {

	@Autowired
	private PhoneAlertService phoneAlertService;
	
	@GetMapping("/phoneAlert") 
	public List<String> listOfPhonePerFireStation(@RequestParam String firestation) {
		return phoneAlertService.listOfPhonePerNumFireStation(firestation);
	}
}
