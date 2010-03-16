package org.springframework.samples.petcare.appointments.messaging;

import org.springframework.integration.annotation.Gateway;

public interface AppointmentMessageGateway {

	@Gateway
	void publish(AppointmentMessage message);
	
}
