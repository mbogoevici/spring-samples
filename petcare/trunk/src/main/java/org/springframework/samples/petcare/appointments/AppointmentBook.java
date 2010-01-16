package org.springframework.samples.petcare.appointments;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

public interface AppointmentBook {

	Map<String, List<Appointment>> getAppointmentsForDay(LocalDate day);

	void addAppointment(AppointmentForm appointment);

}