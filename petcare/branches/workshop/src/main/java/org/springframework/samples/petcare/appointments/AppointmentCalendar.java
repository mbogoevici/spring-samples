package org.springframework.samples.petcare.appointments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.samples.petcare.util.EntityReference;

@RooJavaBean(settersByDefault=false)
@RooToString
public class AppointmentCalendar {

	@DateTimeFormat(style="F-")
	private LocalDate day;
	
	private List<EntityReference> doctors;
	
	private List<List<Appointment>> appointments; 
	
	public AppointmentCalendar(LocalDate day) {
		this.day = day;
	}
	
	public void setDoctors(List<EntityReference> doctors) {
		this.doctors = doctors;
		appointments = new ArrayList<List<Appointment>>(9);
		for (int i = 0; i < 9; i++) {
			appointments.add(createAppointmentList(doctors.size()));
		}
	}

	public void addAppointment(Appointment appointment) {
		int blockIndex = appointment.getDateTime().getHourOfDay() - 8;
		int doctorIndex = doctors.indexOf(new EntityReference(appointment.getDoctorId()));
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
	
	public Long getBlockMillis(LocalTime block) {
		return day.toDateTime(block).getMillis();
	}

	public Date getStartOfDay() {
		return day.toDateTime(getBlocks().get(0)).toDate();
	}

	public Date getEndOfDay() {
		return day.toDateTime(getBlocks().get(8)).plusMillis(60).toDate();
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