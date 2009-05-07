package org.springframework.samples.petclinic.owners;

public class OwnerSummary {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private String[] pets;

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public String[] getPets() {
		return pets;
	}
	
}
