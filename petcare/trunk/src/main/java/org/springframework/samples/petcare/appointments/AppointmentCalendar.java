package org.springframework.samples.petcare.appointments;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean(settersByDefault=false)
@RooToString
public class AppointmentCalendar {

	@DateTimeFormat(style="F-")
	private LocalDate day;
	
	private List<DoctorReference> doctors;
	
	private List<List<Appointment>> appointments; 
	
	public AppointmentCalendar(LocalDate day) {
		this.day = day;
	}

	public void setDoctors(List<DoctorReference> doctors) {
		this.doctors = doctors;
		appointments = new ArrayList<List<Appointment>>(Block.count());
		for (int i = 0; i < Block.count(); i++) {
			appointments.add(createAppointmentList(doctors.size()));
		}
	}

	public void addAppointment(Long doctorId, Appointment appointment) {
		int blockIndex = Block.indexOf(appointment);
		int doctorIndex = doctors.indexOf(new DoctorReference(doctorId));
		if (appointments.get(blockIndex).get(doctorIndex) != null) {
			throw new IllegalArgumentException("Already an appointment at block " + blockIndex + " for doctor " + doctorId);
		}
		appointments.get(blockIndex).set(doctorIndex, appointment);
	}
	
	public List<Block> getBlocks() {
		return Block.getBlocks();
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
	
	// internal
	
	private List<Appointment> createAppointmentList(int size) {
		List<Appointment> appointments = new ArrayList<Appointment>(size);
		for (int i = 0; i < size; i++) {
			appointments.add(null);
		}
		return appointments;
	}
	
}
