package org.springframework.samples.petcare.appointments;

import org.joda.time.LocalDate;

public interface AppointmentService {

	AppointmentCalendar getAppointmentsForDay(LocalDate day);

	Long addAppointment(NewAppointment appointment);

	void deleteAppointment(Long appointmentId);

}