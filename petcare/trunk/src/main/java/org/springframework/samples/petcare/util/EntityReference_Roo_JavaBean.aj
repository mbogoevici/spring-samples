package org.springframework.samples.petcare.util;

import java.lang.Long;
import java.lang.String;

privileged aspect EntityReference_Roo_JavaBean {
    
    public Long EntityReference.getId() {
        return this.id;
    }
    
    public String EntityReference.getLabel() {
        return this.label;
    }
    
}
