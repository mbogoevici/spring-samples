package org.springframework.samples.petcare.appointments;

import java.lang.Long;
import java.lang.String;
import org.joda.time.DateTime;

privileged aspect NewAppointment_Roo_JavaBean {
    
    public DateTime NewAppointment.getTime() {    
        return this.time;        
    }    
    
    public void NewAppointment.setTime(DateTime time) {    
        this.time = time;        
    }    
    
    public Long NewAppointment.getPatientId() {    
        return this.patientId;        
    }    
    
    public void NewAppointment.setPatientId(Long patientId) {    
        this.patientId = patientId;        
    }    
    
    public String NewAppointment.getReason() {    
        return this.reason;        
    }    
    
    public void NewAppointment.setReason(String reason) {    
        this.reason = reason;        
    }    
    
}
