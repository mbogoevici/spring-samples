package org.springframework.samples.petclinic.appointments;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JpaAppointmentBook implements AppointmentBook {

	public Appointments getAppointmentsForToday() {
		// TODO Auto-generated method stub
		return null;
	}

	public Appointments getAppointmentsForDay(LocalDate day) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long createAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		return null;
	}

}