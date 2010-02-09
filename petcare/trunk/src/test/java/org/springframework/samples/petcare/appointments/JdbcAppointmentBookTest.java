package org.springframework.samples.petcare.appointments;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
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
public class JdbcAppointmentBookTest {

	@Autowired
	private AppointmentRepository appointmentBook;

	@Test
	@Transactional
	public void testGetAppointmentsForDay() {
		AppointmentCalendar calendar = appointmentBook.getAppointmentsForDay(new LocalDate(2009, 10, 23));
		assertEquals(2, calendar.getDoctorAppointments().size());
	}

	@Test
	@Transactional
	@Ignore
	public void addAppointment() {
		NewAppointment form = new NewAppointment();
		appointmentBook.addAppointment(form);
		AppointmentCalendar calendar = appointmentBook.getAppointmentsForDay(new LocalDate(2009, 12, 29));
		assertEquals(1, calendar.getDoctorAppointments().size());
		Appointment a = calendar.getDoctorAppointments().get("Dwight Howard").iterator().next();
		assertEquals(new DateTime(2009, 12, 29, 8, 0, 0, 0), a.getTime());
		assertEquals("Macy", a.getPatient());
		assertEquals("Keith Donald", a.getClient());
		assertEquals("1-205-333-5555", a.getClientPhone());
	}

}
