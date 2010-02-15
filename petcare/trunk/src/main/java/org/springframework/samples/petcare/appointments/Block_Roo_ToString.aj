package org.springframework.samples.petcare.appointments;

import java.lang.String;

privileged aspect Block_Roo_ToString {
    
    public String Block.toString() {    
        StringBuilder sb = new StringBuilder();        
        sb.append("Time: ").append(getTime());        
        return sb.toString();        
    }    
    
}
