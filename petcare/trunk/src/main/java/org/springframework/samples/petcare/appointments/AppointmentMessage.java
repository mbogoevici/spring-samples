package org.springframework.samples.petcare.appointments;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

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
