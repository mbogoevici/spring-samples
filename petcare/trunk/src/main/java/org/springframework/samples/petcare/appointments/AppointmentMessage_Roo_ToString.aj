package org.springframework.samples.petcare.appointments;

import java.lang.String;

privileged aspect AppointmentMessage_Roo_ToString {
    
    public String AppointmentMessage.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: ").append(getType()).append(", ");
        sb.append("Appointment: ").append(getAppointment());
        return sb.toString();
    }
    
}
