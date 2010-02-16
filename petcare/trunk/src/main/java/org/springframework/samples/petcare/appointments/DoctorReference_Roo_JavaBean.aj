package org.springframework.samples.petcare.appointments;

import java.lang.Long;
import java.lang.String;

privileged aspect DoctorReference_Roo_JavaBean {
    
    public Long DoctorReference.getId() {    
        return this.id;        
    }    
    
    public String DoctorReference.getName() {    
        return this.name;        
    }    
    
}
