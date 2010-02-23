package org.springframework.samples.petcare.appointments;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class NewAppointment {

	@NotNull
	private Long dateTime;
	
	@NotNull
	private Long patientId;

	@NotNull
	private Long doctorId;
	
	@Max(value=255)
	private String reason;

}
