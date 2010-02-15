package org.springframework.samples.petcare.appointments;

import org.joda.time.LocalTime;

privileged aspect Block_Roo_JavaBean {
    
    public LocalTime Block.getTime() {    
        return this.time;        
    }    
    
}
