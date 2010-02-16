package org.springframework.samples.petcare.appointments;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class NewAppointment {

	@Future
	@NotNull
	private DateTime time;

	@NotNull
	private Long patientId;

	@NotNull
	private Long doctorId;
	
	@NotNull
	private String reason;

}
