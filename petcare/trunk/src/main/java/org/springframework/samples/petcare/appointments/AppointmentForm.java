package org.springframework.samples.petcare.appointments;

import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class AppointmentForm {

	@NotNull
	private Long patientId;

	@NotNull
	private String reason;

}
