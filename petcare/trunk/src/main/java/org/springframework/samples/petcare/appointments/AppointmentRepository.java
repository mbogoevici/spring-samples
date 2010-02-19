package org.springframework.samples.petcare.appointments;

import org.joda.time.LocalDate;

public interface AppointmentRepository {

	AppointmentCalendar getAppointmentsForDay(LocalDate day);

	Long createAppointment(NewAppointment appointment);

	void deleteAppointment(Long appointmentId);

}