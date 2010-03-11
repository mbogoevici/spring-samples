package org.springframework.samples.petcare.appointments;

import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.samples.petcare.appointments.Appointment;
import org.springframework.samples.petcare.util.EntityReference;

privileged aspect AppointmentCalendar_Roo_JavaBean {
    
    public LocalDate AppointmentCalendar.getDay() {
        return this.day;
    }
    
    public List<EntityReference> AppointmentCalendar.getDoctors() {
        return this.doctors;
    }
    
    public List<List<Appointment>> AppointmentCalendar.getAppointments() {
        return this.appointments;
    }
    
}
