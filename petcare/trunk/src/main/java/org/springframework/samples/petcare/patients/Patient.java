package org.springframework.samples.petcare.patients;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
// consider roo entity here
public class Patient {
	
	private Long id;
	
	private String name;
	
	private String client;
	
}
