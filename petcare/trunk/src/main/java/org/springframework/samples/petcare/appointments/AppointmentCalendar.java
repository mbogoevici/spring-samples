package org.springframework.samples.petcare.appointments;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.samples.petcare.util.ResourceReference;

@RooJavaBean(settersByDefault=false)
@RooToString
public class AppointmentCalendar {

	@DateTimeFormat(style="F-")
	private LocalDate day;
	
	private List<ResourceReference> doctors;
	
	private List<List<Appointment>> appointments; 
	
	public AppointmentCalendar(LocalDate day) {
		this.day = day;
	}

	public void setDoctors(List<ResourceReference> doctors) {
		this.doctors = doctors;
		appointments = new ArrayList<List<Appointment>>(9);
		for (int i = 0; i < 9; i++) {
			appointments.add(createAppointmentList(doctors.size()));
		}
	}

	public void addAppointment(Long doctorId, Appointment appointment) {
		int blockIndex = appointment.getStartTime().getHourOfDay() - 8;
		int doctorIndex = doctors.indexOf(new ResourceReference(doctorId));
		if (appointments.get(blockIndex).get(doctorIndex) != null) {
			throw new IllegalArgumentException("Already an appointment at block " + blockIndex + " for doctor " + doctorId);
		}
		appointments.get(blockIndex).set(doctorIndex, appointment);
	}

	public Long getDayMillis() {
		return day.toDateTimeAtStartOfDay().getMillis();
	}
	
	public List<LocalTime> getBlocks() {
		List<LocalTime> blocks = new ArrayList<LocalTime>(9);
		LocalTime time = new LocalTime(8, 0);
		for (int i = 0; i < 9; i++) {
			blocks.add(time);
			time = time.plusMinutes(60);
		}
		return blocks;
	}
	
	// resource links
	
	public LocalDate getPreviousDay() {
		return day.minusDays(1);
	}

	public LocalDate getNextDay() {
		return day.plusDays(1);
	}
	
	// internal
	
	private List<Appointment> createAppointmentList(int size) {
		List<Appointment> appointments = new ArrayList<Appointment>(size);
		for (int i = 0; i < size; i++) {
			appointments.add(null);
		}
		return appointments;
	}
	
}