package org.springframework.samples.petcare.appointments;

import java.lang.String;

privileged aspect AppointmentCalendar_Roo_ToString {
    
    public String AppointmentCalendar.toString() {    
        StringBuilder sb = new StringBuilder();        
        sb.append("Blocks: ").append(getBlocks() == null ? "null" : getBlocks().size()).append(", ");        
        sb.append("PreviousDayResourceId: ").append(getPreviousDayResourceId()).append(", ");        
        sb.append("NextDayResourceId: ").append(getNextDayResourceId()).append(", ");        
        sb.append("Day: ").append(getDay()).append(", ");        
        sb.append("Doctors: ").append(getDoctors() == null ? "null" : getDoctors().size()).append(", ");        
        sb.append("Appointments: ").append(getAppointments() == null ? "null" : getAppointments().size());        
        return sb.toString();        
    }    
    
}
