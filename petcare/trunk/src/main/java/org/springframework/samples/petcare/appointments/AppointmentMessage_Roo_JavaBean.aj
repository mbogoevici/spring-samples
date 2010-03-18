package org.springframework.samples.petcare.appointments;

import org.springframework.samples.petcare.appointments.Appointment;
import org.springframework.samples.petcare.appointments.AppointmentMessage.MessageType;

privileged aspect AppointmentMessage_Roo_JavaBean {
    
    public MessageType AppointmentMessage.getType() {
        return this.type;
    }
    
    public Appointment AppointmentMessage.getAppointment() {
        return this.appointment;
    }
    
}
