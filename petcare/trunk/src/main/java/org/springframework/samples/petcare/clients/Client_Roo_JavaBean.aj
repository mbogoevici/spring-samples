package org.springframework.samples.petcare.clients;

import java.lang.Long;
import java.lang.String;
import org.springframework.samples.petcare.util.Address;

privileged aspect Client_Roo_JavaBean {
    
    public Long Client.getId() {
        return this.id;
    }
    
    public void Client.setId(Long id) {
        this.id = id;
    }
    
    public String Client.getFirstName() {
        return this.firstName;
    }
    
    public void Client.setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String Client.getLastName() {
        return this.lastName;
    }
    
    public void Client.setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String Client.getPhone() {
        return this.phone;
    }
    
    public void Client.setPhone(String phone) {
        this.phone = phone;
    }
    
    public Address Client.getAddress() {
        return this.address;
    }
    
    public void Client.setAddress(Address address) {
        this.address = address;
    }
    
}
