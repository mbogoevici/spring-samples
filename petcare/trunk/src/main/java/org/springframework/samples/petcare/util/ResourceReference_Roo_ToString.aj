package org.springframework.samples.petcare.util;

import java.lang.String;

privileged aspect ResourceReference_Roo_ToString {
    
    public String ResourceReference.toString() {    
        StringBuilder sb = new StringBuilder();        
        sb.append("Id: ").append(getId()).append(", ");        
        sb.append("Label: ").append(getLabel());        
        return sb.toString();        
    }    
    
}
