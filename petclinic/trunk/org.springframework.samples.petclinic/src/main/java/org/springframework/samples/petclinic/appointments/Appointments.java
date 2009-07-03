package org.springframework.samples.petclinic.appointments;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Appointments {

	private final Map<String, List<Appointment>> vetAppointments;

	public Appointments(Map<String, List<Appointment>> appointmentList) {
		this.vetAppointments = appointmentList;
	}
	
	public Map<String, List<Appointment>> getAllByVet() {
		return Collections.unmodifiableMap(vetAppointments);
	}
}