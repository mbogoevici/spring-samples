package org.springframework.samples.petcare.appointments;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class AppointmentCalendar {

	private final LocalDate day;

	private final Map<String, List<Appointment>> doctorAppointments;

	public AppointmentCalendar(LocalDate day, Map<String, List<Appointment>> doctorAppointments) {
		this.day = day;
		this.doctorAppointments = Collections.unmodifiableMap(doctorAppointments);
	}

	@DateTimeFormat(style="F-")
	public LocalDate getDay() {
		return day;
	}
	
	public boolean getHasAppointments() {
		return !doctorAppointments.isEmpty();
	}

	public Map<String, List<Appointment>> getDoctorAppointments() {
		return doctorAppointments;
	}
	
	// resource links
	
	@DateTimeFormat(iso=ISO.DATE)
	public LocalDate getPreviousDayResourceId() {
		return day.minusDays(1);
	}

	@DateTimeFormat(iso=ISO.DATE)
	public LocalDate getNextDayResourceId() {
		return day.plusDays(1);
	}

}
