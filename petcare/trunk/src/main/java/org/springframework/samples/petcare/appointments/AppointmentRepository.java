package org.springframework.samples.petcare.appointments;

import org.joda.time.LocalDate;

public interface AppointmentRepository {

	AppointmentCalendar getAppointmentsForDay(LocalDate day);

	void createAppointment(NewAppointment appointment);

}