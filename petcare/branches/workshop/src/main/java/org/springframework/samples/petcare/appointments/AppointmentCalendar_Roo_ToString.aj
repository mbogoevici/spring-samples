package org.springframework.samples.petcare.appointments;

import java.lang.String;

privileged aspect AppointmentCalendar_Roo_ToString {
    
    public String AppointmentCalendar.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DayMillis: ").append(getDayMillis()).append(", ");
        sb.append("Blocks: ").append(getBlocks() == null ? "null" : getBlocks().size()).append(", ");
        sb.append("StartOfDay: ").append(getStartOfDay()).append(", ");
        sb.append("EndOfDay: ").append(getEndOfDay()).append(", ");
        sb.append("PreviousDay: ").append(getPreviousDay()).append(", ");
        sb.append("NextDay: ").append(getNextDay()).append(", ");
        sb.append("Day: ").append(getDay()).append(", ");
        sb.append("Doctors: ").append(getDoctors() == null ? "null" : getDoctors().size()).append(", ");
        sb.append("Appointments: ").append(getAppointments() == null ? "null" : getAppointments().size());
        return sb.toString();
    }
    
}
