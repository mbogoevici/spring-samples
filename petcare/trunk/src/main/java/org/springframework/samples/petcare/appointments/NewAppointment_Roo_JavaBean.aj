package org.springframework.samples.petcare.appointments;

import java.lang.Long;
import java.lang.String;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

privileged aspect NewAppointment_Roo_JavaBean {
    
    public Long NewAppointment.getPatientId() {    
        return this.patientId;        
    }    
    
    public void NewAppointment.setPatientId(Long patientId) {    
        this.patientId = patientId;        
    }    
    
    public Long NewAppointment.getDoctorId() {    
        return this.doctorId;        
    }    
    
    public void NewAppointment.setDoctorId(Long doctorId) {    
        this.doctorId = doctorId;        
    }    
    
    public LocalDate NewAppointment.getDay() {    
        return this.day;        
    }    
    
    public void NewAppointment.setDay(LocalDate day) {    
        this.day = day;        
    }    
    
    public LocalTime NewAppointment.getTime() {    
        return this.time;        
    }    
    
    public void NewAppointment.setTime(LocalTime time) {    
        this.time = time;        
    }    
    
    public String NewAppointment.getReason() {    
        return this.reason;        
    }    
    
    public void NewAppointment.setReason(String reason) {    
        this.reason = reason;        
    }    
    
}
