package org.springframework.samples.petclinic.appointments;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String owner;
	
	private String ownerPhone;
	
	private String pet;
	
	private String notes;
	
	private Date dateTime;
	
	@SuppressWarnings("unused")
	private Appointment(){
		// Needed by ORM Engines
	}
	
	public Appointment(String owner, String ownerPhone, String pet, String notes, Date dateTime) {
		this.owner = owner;
		this.ownerPhone = ownerPhone;
		this.pet = pet;
		this.notes = notes;
		this.dateTime = dateTime;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public String getOwnerPhone() {
		return ownerPhone;
	}

	public String getPet() {
		return pet;
	}
	
	public Date getDateTime() {
		return dateTime;
	}

	public String getNotes() {
		return notes;
	}
	
	public Long getId() {
		return id;
	}
}