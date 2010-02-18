package org.springframework.samples.petcare.appointments;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class NewAppointment {

	@Future
	@NotNull
	private LocalDate day;

	@NotNull
	private LocalTime time;
	
	@NotNull
	private Long patientId;

	@NotNull
	private Long doctorId;
	
	private String reason;

}
