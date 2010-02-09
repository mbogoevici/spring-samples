package org.springframework.samples.petcare.patients;

import java.lang.String;

privileged aspect Patient_Roo_ToString {
    
    public String Patient.toString() {    
        StringBuilder sb = new StringBuilder();        
        sb.append("Id: ").append(getId()).append(", ");        
        sb.append("Name: ").append(getName()).append(", ");        
        sb.append("Client: ").append(getClient());        
        return sb.toString();        
    }    
    
}
