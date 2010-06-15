package org.springframework.samples.petcare.util;

import java.lang.String;

privileged aspect Address_Roo_JavaBean {
    
    public String Address.getStreet() {
        return this.street;
    }
    
    public void Address.setStreet(String street) {
        this.street = street;
    }
    
    public String Address.getCity() {
        return this.city;
    }
    
    public void Address.setCity(String city) {
        this.city = city;
    }
    
    public String Address.getState() {
        return this.state;
    }
    
    public void Address.setState(String state) {
        this.state = state;
    }
    
    public String Address.getZip() {
        return this.zip;
    }
    
    public void Address.setZip(String zip) {
        this.zip = zip;
    }
    
}
