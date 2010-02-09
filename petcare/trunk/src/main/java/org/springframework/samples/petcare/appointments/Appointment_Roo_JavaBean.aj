package org.springframework.samples.petcare.appointments;

import java.lang.String;
import org.joda.time.DateTime;

privileged aspect Appointment_Roo_JavaBean {
    
    public DateTime Appointment.getTime() {    
        return this.time;        
    }    
    
    public void Appointment.setTime(DateTime time) {    
        this.time = time;        
    }    
    
    public String Appointment.getPatient() {    
        return this.patient;        
    }    
    
    public void Appointment.setPatient(String patient) {    
        this.patient = patient;        
    }    
    
    public String Appointment.getClient() {    
        return this.client;        
    }    
    
    public void Appointment.setClient(String client) {    
        this.client = client;        
    }    
    
    public String Appointment.getClientPhone() {    
        return this.clientPhone;        
    }    
    
    public void Appointment.setClientPhone(String clientPhone) {    
        this.clientPhone = clientPhone;        
    }    
    
    public String Appointment.getReason() {    
        return this.reason;        
    }    
    
    public void Appointment.setReason(String reason) {    
        this.reason = reason;        
    }    
    
}
