package org.springframework.samples.petcare.appointments.messaging;

import org.springframework.samples.petcare.appointments.Appointment;
import org.springframework.samples.petcare.appointments.messaging.AppointmentMessage.MessageType;

privileged aspect AppointmentMessage_Roo_JavaBean {
    
    public MessageType AppointmentMessage.getType() {
        return this.type;
    }
    
    public Appointment AppointmentMessage.getAppointment() {
        return this.appointment;
    }
    
}
