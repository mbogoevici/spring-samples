package org.springframework.samples.petcare.patients;

import java.lang.Long;
import java.lang.String;

privileged aspect Patient_Roo_JavaBean {
    
    public Long Patient.getId() {    
        return this.id;        
    }    
    
    public void Patient.setId(Long id) {    
        this.id = id;        
    }    
    
    public String Patient.getName() {    
        return this.name;        
    }    
    
    public void Patient.setName(String name) {    
        this.name = name;        
    }    
    
    public String Patient.getClient() {    
        return this.client;        
    }    
    
    public void Patient.setClient(String client) {    
        this.client = client;        
    }    
    
}
