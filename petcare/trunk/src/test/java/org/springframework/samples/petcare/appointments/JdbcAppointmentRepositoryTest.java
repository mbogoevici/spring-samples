package org.springframework.samples.petcare.appointments;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JdbcAppointmentRepositoryTest {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Test
	@Transactional
	public void testGetAppointmentsForDay() {
		AppointmentCalendar calendar = appointmentRepository.getAppointmentsForDay(new LocalDate(2010, 02, 16));
		assertEquals(new LocalDate(2010, 02, 16), calendar.getDay());
		assertEquals("Macy", calendar.getAppointments().get(0).get(0).getPatient());
		assertEquals("Lil Jerry", calendar.getAppointments().get(8).get(1).getPatient());
	}

	@Test
	@Transactional
	@Ignore
	public void addAppointment() {
		NewAppointment form = new NewAppointment();
		appointmentRepository.addAppointment(form);
		appointmentRepository.getAppointmentsForDay(new LocalDate(2009, 12, 29));
	}

}
