package com.SafetyNet.alerts.repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.model.Store;

@Service
public class PersonRepository {
	
	private Store store;
	
	@Autowired
	public PersonRepository(Store store) {
		
		this.store = store;
	}

	public List<Person> findAll() {
		return store.getPersons();
	}
	
	public List<Person> findByFirstName(String firstName) {

		return store.getPersons().stream()
//				.map(Person::getFirstName)
//				.map(p -> p.getFirstName())
				.filter(fm -> firstName.equals(fm.getFirstName()))
				.collect(Collectors.toList());
	}
	
	public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
		
		return store.getPersons().stream()
				.filter(fm -> firstName.equals(fm.getFirstName()))
				.filter(ln -> lastName.equals(ln.getLastName()))
				.collect(Collectors.toList());
	}
	
	public Person findPersonByFirstNameAndLastName(String firstName, String lastName) {
		
		Person person = new Person();
		List<Person> persons = findAll();
		for (Person i : persons) {
			if (i.getFirstName().contains(firstName) & i.getLastName().contains(lastName)) {
				person = i;	
			}
		}
		return person;
	}
	
	public List<Person> findByAddressIn(Set<String> addresses) {

		return store.getPersons().stream()
				.filter(a -> addresses.contains(a.getAddress()))
				.collect(Collectors.toList());
	}
	
	public List<Person> findByAddress(String address) {
		
		return store.getPersons().stream().filter(p -> address
				.equals(p.getAddress())).collect(Collectors.toList());
	}
	
	public List<Person> findAllByCity(String city) {
		
		return store.getPersons().stream()
				.filter(c -> city.equals(c.getCity()))
				.collect(Collectors.toList());
	}
}
