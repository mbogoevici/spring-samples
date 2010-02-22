package org.springframework.samples.petcare.appointments;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class NewAppointment {

	@NotNull
	private Long patientId;

	@NotNull
	private Long doctorId;
	
	@DateTimeFormat(style="S-")
	@NotNull
	private LocalDate day;

	@DateTimeFormat(style="-S")
	@NotNull
	private LocalTime time;
	
	@Max(value=255)
	private String reason;

}
