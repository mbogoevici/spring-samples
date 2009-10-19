package org.springframework.samples.petclinic.appointments;

import org.joda.time.DateTime;

public class Appointment {

	private DateTime dateTime;

	private String doctor;

	private String client;

	private String clientPhone;

	private String patient;

	private String notes;

	Appointment() {

	}

	public Appointment(DateTime dateTime, String doctor, String client,
			String clientPhone, String patient, String notes) {
		this.dateTime = dateTime;
		this.doctor = doctor;
		this.client = client;
		this.clientPhone = clientPhone;
		this.patient = patient;
		this.notes = notes;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public String getDoctor() {
		return doctor;
	}

	public String getClient() {
		return client;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public String getPatient() {
		return patient;
	}

	public String getNotes() {
		return notes;
	}

}