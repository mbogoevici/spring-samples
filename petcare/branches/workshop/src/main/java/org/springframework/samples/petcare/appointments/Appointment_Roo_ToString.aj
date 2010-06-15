package org.springframework.samples.petcare.appointments;

import java.lang.String;

privileged aspect Appointment_Roo_ToString {
    
    public String Appointment.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("DateTime: ").append(getDateTime()).append(", ");
        sb.append("DoctorId: ").append(getDoctorId()).append(", ");
        sb.append("Patient: ").append(getPatient()).append(", ");
        sb.append("Client: ").append(getClient()).append(", ");
        sb.append("ClientPhone: ").append(getClientPhone()).append(", ");
        sb.append("Reason: ").append(getReason());
        return sb.toString();
    }
    
}
