package org.springframework.samples.petcare.appointments;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class NewAppointment {

	private Long patientId;

	private Long doctorId;
	
	@DateTimeFormat(style="S-")
	private LocalDate day;

	@DateTimeFormat(style="-S")
	private LocalTime time;
	
	private String reason;

}
