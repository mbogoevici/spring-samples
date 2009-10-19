package org.springframework.samples.petclinic.appointments;

import org.joda.time.LocalDate;

public interface AppointmentBook {

	Appointments getAppointmentsForToday();

	Appointments getAppointmentsForDay(LocalDate day);

	Long createAppointment(Appointment appointment);

}