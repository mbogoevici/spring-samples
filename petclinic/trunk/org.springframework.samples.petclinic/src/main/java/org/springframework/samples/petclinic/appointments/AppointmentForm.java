package org.springframework.samples.petclinic.appointments;

import org.joda.time.DateTime;

public class AppointmentForm {

	private DateTime dateTime;

	private String notes;

	private Long patientId;

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

}
