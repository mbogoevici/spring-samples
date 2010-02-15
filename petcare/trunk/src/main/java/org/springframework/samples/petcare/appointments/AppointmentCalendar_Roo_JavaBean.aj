package org.springframework.samples.petcare.appointments;

import java.lang.String;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.joda.time.LocalDate;
import org.springframework.samples.petcare.appointments.Appointment;
import org.springframework.samples.petcare.appointments.Block;

privileged aspect AppointmentCalendar_Roo_JavaBean {
    
    public LocalDate AppointmentCalendar.getDay() {    
        return this.day;        
    }    
    
    public Map<String, List<Appointment>> AppointmentCalendar.getDoctorAppointments() {    
        return this.doctorAppointments;        
    }    
    
    public Set<Block> AppointmentCalendar.getBlocks() {    
        return this.blocks;        
    }    
    
}
