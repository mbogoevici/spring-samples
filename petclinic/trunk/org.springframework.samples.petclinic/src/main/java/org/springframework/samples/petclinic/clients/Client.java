package org.springframework.samples.petclinic.clients;

import org.springframework.samples.petclinic.util.Address;

public class Client {

	private Long id;

	private String firstName;

	private String lastName;

	private String phone;

	private Address address;

	public Client() {

	}

	public Client(Long id) {
		this.id = id;
	}

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

	public Address getAddress() {
		return address;
	}

}