package org.springframework.samples.petcare.appointments;

import java.lang.Long;
import java.lang.String;

privileged aspect NewAppointment_Roo_JavaBean {
    
    public Long NewAppointment.getDateTime() {
        return this.dateTime;
    }
    
    public void NewAppointment.setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }
    
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
    
    public String NewAppointment.getReason() {
        return this.reason;
    }
    
    public void NewAppointment.setReason(String reason) {
        this.reason = reason;
    }
    
}
