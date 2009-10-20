package org.springframework.samples.petclinic.appointments;

import org.joda.time.LocalDate;

public interface AppointmentBook {

	DoctorAppointments getAppointmentsForDay(LocalDate day);

	void addAppointment(AppointmentForm appointment);

}