package com.SafetyNet.alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.dto.PersonInfoDTO;
import com.SafetyNet.alerts.service.PersonInfoService;

@RestController
public class PersonInfoController {

	@Autowired
	private PersonInfoService personInfoService;
	
	@GetMapping("/personInfo")
	public PersonInfoDTO personInfo(@RequestParam String firstName, String lastName) {
		return personInfoService.personInfo(firstName, lastName);
	}
}
