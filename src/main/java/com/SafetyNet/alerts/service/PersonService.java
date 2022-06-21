package com.SafetyNet.alerts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.dto.UpdatePersonRequest;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		
		this.personRepository = personRepository;
	}

	public Optional<Person> getPerson(String name) {
		String firstName = name.split(" ")[0];
		String lastName= name.split(" ")[1];
		return personRepository.findByFirstNameAndLastName(firstName, lastName);
	}
	
	public Optional<Person> createPerson(Person personToCreate) {
		return getPerson(personToCreate.getFirstName() + " " + personToCreate.getLastName())
				.map(person -> {
					
					person.setFirstName(personToCreate.getFirstName());
					
					person.setLastName(personToCreate.getLastName());
					
					person.setAddress(personToCreate.getAddress());
					
					person.setCity(personToCreate.getCity());

					person.setZip(personToCreate.getZip());
					
					person.setPhone(personToCreate.getPhone());
					
					person.setEmail(personToCreate.getEmail());

					return person;
				});
	}
	
	public Optional<Person> updatePerson(String name, UpdatePersonRequest personToUpdate) {
		return getPerson(name).map(person -> {
			
			person.setAddress(personToUpdate.getAddress());
			
			person.setCity(personToUpdate.getCity());

			person.setZip(personToUpdate.getZip());
			
			person.setPhone(personToUpdate.getPhone());
			
			Optional.ofNullable(personToUpdate.getEmail()).ifPresent(email -> person.setEmail(email));

			return person;
		});
	}
	
	public void deletePerson(String name) {
		getPerson(name);
		Optional.ofNullable(null);
	}
}
