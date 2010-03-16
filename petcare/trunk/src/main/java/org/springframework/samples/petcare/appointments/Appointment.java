package org.springframework.samples.petcare.appointments;

import org.joda.time.DateTime;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class Appointment {

	private Long id;

	private DateTime dateTime;

	private Long doctorId;

	private String patient;

	private String client;

	private String clientPhone;

	private String reason;

}