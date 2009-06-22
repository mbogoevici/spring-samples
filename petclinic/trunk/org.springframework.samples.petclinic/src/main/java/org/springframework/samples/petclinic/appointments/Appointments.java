package org.springframework.samples.petclinic.appointments;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Appointments {
	
	private Map<String, List<Appointment>> vetAppointments = new LinkedHashMap<String, List<Appointment>>();
	
	public Appointments(List<Appointment> appointmentList) {
		//TODO: We need to do this in the DB, holding here until its clear we need this
		for (Appointment appointment : appointmentList) {
			if(vetAppointments.containsKey(appointment.getPet())){
				vetAppointments.get(appointment.getPet()).add(appointment);
			}else{
				List<Appointment> appList = new ArrayList<Appointment>();
				appList.add(appointment);
				vetAppointments.put(appointment.getPet(), appList);
			}
			
		}
	}
	
	public Map<String, List<Appointment>> getAllByVet() {
		return vetAppointments;
	}
	
}
