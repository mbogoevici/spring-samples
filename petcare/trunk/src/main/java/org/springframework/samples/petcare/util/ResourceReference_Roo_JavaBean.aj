package org.springframework.samples.petcare.util;

import java.lang.Long;
import java.lang.String;

privileged aspect ResourceReference_Roo_JavaBean {
    
    public Long ResourceReference.getId() {
        return this.id;
    }
    
    public String ResourceReference.getLabel() {
        return this.label;
    }
    
}
