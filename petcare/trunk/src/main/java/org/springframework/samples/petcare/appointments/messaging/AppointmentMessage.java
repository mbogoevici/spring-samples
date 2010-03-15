package org.springframework.samples.petcare.appointments.messaging;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.samples.petcare.appointments.Appointment;

@RooJavaBean(settersByDefault=false)
@RooToString
public final class AppointmentMessage {

	private MessageType type;
	
	private Appointment appointment;
	
	public AppointmentMessage(MessageType type, Appointment appointment) {
		this.type = type;
		this.appointment = appointment;
	}
		
	public enum MessageType {
		APPOINTMENT_ADDED, APPOINTMENT_DELETED
	}
}
