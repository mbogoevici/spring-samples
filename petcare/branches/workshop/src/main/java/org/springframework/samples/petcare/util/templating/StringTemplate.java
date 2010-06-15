package org.springframework.samples.petcare.util.templating;

public interface StringTemplate {
	
	String getName();
	
	void setAttribute(String name, Object value);
	
	String toString();
		
}
