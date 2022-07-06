package com.SafetyNet.alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.dto.UpdatePersonRequest;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	/**
	 * Create - Add a new person
	 * @param person An object person
	 */
	@PostMapping("/person")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(personService.createPerson(person));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	/**
	 * Update - Update an existing person
	 * @param person - The person object updated
	 * @return
	 */
	@PutMapping("/person/{name}")
	public ResponseEntity<Person> updatePerson(@PathVariable("name") String name, 
		@RequestBody UpdatePersonRequest person) {
		return personService.updatePerson(name, person).map(p -> ResponseEntity.ok(p))
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Delete - Delete a person
	 * 
	 */
	@DeleteMapping("/person/{name}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable("name") final String name) {
		personService.deletePerson(name);
	}
	
}


