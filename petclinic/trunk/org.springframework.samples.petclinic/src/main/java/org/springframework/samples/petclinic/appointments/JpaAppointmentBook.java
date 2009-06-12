package org.springframework.samples.petclinic.appointments;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
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
		List<Appointment> resultList = entityManager.createQuery("from Appointment a where a.dateTime = :dateTime")
				.setParameter("dateTime", day).getResultList();
		
		return new Appointments(resultList);
	}

	public Appointments getAppointmentsForToday() {
		return this.getAppointmentsForDay(today());
	}
	
	private Date today(){
		Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"),Locale.GERMANY);
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
}