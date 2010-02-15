package org.springframework.samples.petcare.appointments;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	private Map<String, List<Appointment>> doctorAppointments;

	private Set<Block> blocks;
	
	public AppointmentCalendar(LocalDate day, Map<String, List<Appointment>> doctorAppointments) {
		this.day = day;
		this.doctorAppointments = Collections.unmodifiableMap(doctorAppointments);
		this.blocks = Block.createWorkDayBlocks();
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
