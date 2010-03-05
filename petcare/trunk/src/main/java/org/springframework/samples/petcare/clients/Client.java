package org.springframework.samples.petcare.clients;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.samples.petcare.util.Address;

@RooJavaBean
public class Client {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private Address address;
	
}
