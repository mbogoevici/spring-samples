package org.springframework.samples.petcare.notifications;

import java.lang.String;

privileged aspect Notification_Roo_JavaBean {
    
    public String Notification.getText() {    
        return this.text;        
    }    
    
    public void Notification.setText(String text) {    
        this.text = text;        
    }    
    
}
