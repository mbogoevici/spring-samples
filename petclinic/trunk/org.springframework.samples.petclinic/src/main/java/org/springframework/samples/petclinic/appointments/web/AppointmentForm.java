package org.springframework.samples.petclinic.appointments.web;

import java.util.Date;

import org.springframework.samples.petclinic.appointments.Appointment;

public class AppointmentForm {
	
	private Long doctor;
	
	private Long owner;
	
	private String pet;
	
	private Date date;
	
	private Date time;
	
	private String notes;
	
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getDoctor() {
		return doctor;
	}

	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public String getPet() {
		return pet;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Appointment createAppointment(){
		return new Appointment(this.owner.toString(),this.phone,this.pet,this.notes,this.date);
	}
}