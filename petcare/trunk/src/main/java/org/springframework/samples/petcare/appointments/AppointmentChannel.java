package org.springframework.samples.petcare.appointments;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.integration.core.Message;

public interface AppointmentChannel {

	void setDay(LocalDate day);

	List<Message<?>> pollMessages();

}