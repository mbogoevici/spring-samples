package org.springframework.samples.petcare.appointments;

import java.lang.String;

privileged aspect AppointmentCalendar_Roo_ToString {
    
    public String AppointmentCalendar.toString() {    
        StringBuilder sb = new StringBuilder();        
        sb.append("PreviousDayResourceId: ").append(getPreviousDayResourceId()).append(", ");        
        sb.append("NextDayResourceId: ").append(getNextDayResourceId()).append(", ");        
        sb.append("Day: ").append(getDay()).append(", ");        
        sb.append("DoctorAppointments: ").append(getDoctorAppointments() == null ? "null" : getDoctorAppointments().size()).append(", ");        
        sb.append("Blocks: ").append(getBlocks() == null ? "null" : getBlocks().size());        
        return sb.toString();        
    }    
    
}
