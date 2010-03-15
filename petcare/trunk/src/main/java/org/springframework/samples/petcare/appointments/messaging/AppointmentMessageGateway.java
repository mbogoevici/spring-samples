package org.springframework.samples.petcare.appointments.messaging;

import org.springframework.integration.annotation.Gateway;

public interface AppointmentMessageGateway {

	@Gateway(requestChannel="globalMessageChannel")
	void publish(AppointmentMessage message);
	
}
