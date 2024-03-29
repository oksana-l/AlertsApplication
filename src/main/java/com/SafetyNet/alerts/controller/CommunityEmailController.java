package com.SafetyNet.alerts.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.service.CommunityEmailService;

@RestController
public class CommunityEmailController {

	@Autowired
	private CommunityEmailService communityEmailService;
	
	@GetMapping("/communityEmail")
	public Set<String> listOfMails(@RequestParam String city) {
		return communityEmailService.listOfMails(city);
	}
}
