package org.springframework.samples.petclinic.appointments;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String owner;
	
	@Column
	private String ownerPhone;
	
	@Column
	private String pet;
	
	@Column
	private String notes;
	
	@Column
	private Date dateTime;
	
	@SuppressWarnings("unused")
	private Appointment(){
		//Needed by ORM Engines
	}
	
	public Appointment(String owner, String ownerPhone, String pet, String notes, Date dateTime) {
		super();
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