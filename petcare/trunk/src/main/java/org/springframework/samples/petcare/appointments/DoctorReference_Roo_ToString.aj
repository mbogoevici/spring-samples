package org.springframework.samples.petcare.appointments;

import java.lang.String;

privileged aspect DoctorReference_Roo_ToString {
    
    public String DoctorReference.toString() {    
        StringBuilder sb = new StringBuilder();        
        sb.append("Id: ").append(getId()).append(", ");        
        sb.append("Name: ").append(getName());        
        return sb.toString();        
    }    
    
}
