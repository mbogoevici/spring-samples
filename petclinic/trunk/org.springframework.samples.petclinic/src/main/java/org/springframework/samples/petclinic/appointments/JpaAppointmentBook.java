package org.springframework.samples.petclinic.appointments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JpaAppointmentBook implements AppointmentBook {

	@PersistenceContext
	private EntityManager entityManager;

	public Long createAppointment(Appointment appointment) {
		entityManager.persist(appointment);
		return appointment.getId();
	}

	public Appointments getAppointmentsForDay(Date day) {
		
		@SuppressWarnings("unchecked")
		List<Appointment> appointmentList = entityManager.createQuery("select object(a) from Appointment a where a.dateTime = :dateTime order by a.pet")
				.setParameter("dateTime", day).getResultList();
		 
		Map<String, List<Appointment>> vetAppointments = new LinkedHashMap<String, List<Appointment>>();
		for (Appointment appointment : appointmentList) {
			if(vetAppointments.containsKey(appointment.getPet())){
				vetAppointments.get(appointment.getPet()).add(appointment);
			}else{
				List<Appointment> appList = new ArrayList<Appointment>();
				appList.add(appointment);
				vetAppointments.put(appointment.getPet(), appList);
			}
		}
		
		return new Appointments(vetAppointments);
	}

	public Appointments getAppointmentsForToday() {
		return this.getAppointmentsForDay(today());
	}
	
	private Date today(){
		Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
}