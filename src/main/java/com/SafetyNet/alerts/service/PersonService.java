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

	public Person createPerson(Person personToCreate) throws Exception {
		Optional<Person> p = personRepository
				.findByFirstNameAndLastName(personToCreate.getFirstName(), personToCreate.getLastName());
		if (p.isPresent()) {
			throw new Exception("Person alredy exists");
		}
		return personRepository.save(personToCreate);
	}

	public Optional<Person> updatePerson(String name, UpdatePersonRequest personToUpdate) {
		return getPerson(name).map(person -> {

			person.setAddress(personToUpdate.getAddress());

			person.setCity(personToUpdate.getCity());

			person.setZip(personToUpdate.getZip());

			person.setPhone(personToUpdate.getPhone());
			
			person.setEmail(personToUpdate.getEmail());

			return personRepository.save(person);
		});
	}

	public void deletePerson(String name) {
		String firstName = name.split(" ")[0];
		String lastName= name.split(" ")[1];
		personRepository.delete(firstName,lastName);
	}
}
