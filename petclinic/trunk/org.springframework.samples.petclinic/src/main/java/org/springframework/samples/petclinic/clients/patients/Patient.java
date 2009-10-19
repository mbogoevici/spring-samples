package org.springframework.samples.petclinic.clients.patients;

import org.joda.time.DateMidnight;
import org.springframework.samples.petclinic.util.Gender;
import org.springframework.samples.petclinic.util.Measurement;

public class Patient {

	private String name;

	private String species;

	private String breed;

	private Gender gender;

	private DateMidnight birthDate;

	private Measurement weight;

	public String getName() {
		return name;
	}

	public String getSpecies() {
		return species;
	}

	public String getBreed() {
		return breed;
	}

	public Gender getGender() {
		return gender;
	}

	public DateMidnight getBirthDate() {
		return birthDate;
	}

	public Measurement getWeight() {
		return weight;
	}

}
