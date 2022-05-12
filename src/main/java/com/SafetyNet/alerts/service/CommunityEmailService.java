package com.SafetyNet.alerts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class CommunityEmailService {

	@Autowired
	private PersonRepository personRepository;
	
	public List<String> listOfMails(String city) {
		return personRepository.findAllByCity(city).stream()
				.map(p -> p.getEmail()).collect(Collectors.toList());
	}
}
