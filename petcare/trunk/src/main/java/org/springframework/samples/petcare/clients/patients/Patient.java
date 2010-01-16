package org.springframework.samples.petcare.clients.patients;

import org.joda.time.DateMidnight;
import org.springframework.samples.petcare.util.Gender;
import org.springframework.samples.petcare.util.Measurement;

public class Patient {

	private String name;

	private Gender gender;

	private String species;

	private String breed;
	
	private DateMidnight birthDate;

	private Measurement weight;

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public String getSpecies() {
		return species;
	}

	public String getBreed() {
		return breed;
	}

	public DateMidnight getBirthDate() {
		return birthDate;
	}

	public Measurement getWeight() {
		return weight;
	}

}
