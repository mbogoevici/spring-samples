package org.springframework.samples.petcare.appointments;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.integration.core.Message;

@Scope(value="session", proxyMode=ScopedProxyMode.INTERFACES)
public interface AppointmentCalendarMessageStore {

	void setDay(LocalDate day);

	List<Message<?>> pollMessages();

}