package org.springframework.samples.petcare.appointments.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.samples.petcare.appointments.AppointmentMessage;

public interface AppointmentMessageGateway {

	@Gateway
	void publish(AppointmentMessage message);
	
}
