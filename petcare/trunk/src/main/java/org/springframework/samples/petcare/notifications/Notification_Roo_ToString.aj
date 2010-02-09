package org.springframework.samples.petcare.notifications;

import java.lang.String;

privileged aspect Notification_Roo_ToString {
    
    public String Notification.toString() {    
        StringBuilder sb = new StringBuilder();        
        sb.append("Text: ").append(getText());        
        return sb.toString();        
    }    
    
}
