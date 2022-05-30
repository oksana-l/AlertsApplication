package com.SafetyNet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.dto.PersonDTO;
import com.SafetyNet.alerts.service.ChildAlertService;

@RestController
public class ChildAlertController {

	@Autowired
	ChildAlertService childAlertService;
	
	@GetMapping("/childAlert")
	public List<PersonDTO> listOfChildPerAddress(@RequestParam String address) {
		return childAlertService.listOfAdultPerAddress(address);
	}
}
