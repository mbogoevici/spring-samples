package org.springframework.samples.petclinic.appointments;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CachingMapDecorator;

public class DoctorAppointments {

	@SuppressWarnings("serial")
	private final Map<String, Set<Appointment>> doctorAppointments = new CachingMapDecorator<String, Set<Appointment>>(
			new LinkedHashMap<String, Set<Appointment>>()) {
		protected Set<Appointment> create(String doctor) {
			return new LinkedHashSet<Appointment>();
		}
	};

	public DoctorAppointments(List<Appointment> appointments) {
		for (Appointment a : appointments) {
			put(a);
		}
	}
	public Set<String> getDoctors() {
		return this.doctorAppointments.keySet();
	}

	public Set<Appointment> getAppointments(String doctor) {
		return this.doctorAppointments.get(doctor);
	}

	public Map<String, Set<Appointment>> asMap() {
		return Collections.unmodifiableMap(this.doctorAppointments);
	}

	public String toString() {
		return this.doctorAppointments.values().toString();
	}

	private void put(Appointment appointment) {
		this.doctorAppointments.get(appointment.getDoctor()).add(appointment);
	}
	
}