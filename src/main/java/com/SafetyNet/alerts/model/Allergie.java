package com.SafetyNet.alerts.model;

import java.util.ArrayList;
import java.util.List;

public class Allergie {
	private String allergie;

	public Allergie() {
		
	}

	public Allergie(String allergie) {
		super();
		this.allergie = allergie;
	}
	
	public String getAllergie() {
		return allergie;
	}

	public void setAllergie(String allergie) {
		this.allergie = allergie;
	}
	
	public List<String> addToAllergies(String allergie) {
		List<String> allergies = new ArrayList<>();
		allergies.add(allergie);
		return allergies;
	}
}
