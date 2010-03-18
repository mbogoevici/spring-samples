package org.springframework.samples.petcare.appointments;

import java.util.Set;

import org.joda.time.LocalDate;

public interface AppointmentCalendarMessageQueue {

	void setDay(LocalDate day);

	Set<AppointmentMessage> pollMessages();

}