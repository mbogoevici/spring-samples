package org.springframework.samples.petclinic.appointments;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JdbcAppointmentBookTests {

	@Autowired
	private AppointmentBook appointmentBook;

	@Test
	@Transactional
	public void testGetAppointmentsForDay() {
		DoctorAppointments appointments = this.appointmentBook.getAppointmentsForDay(new LocalDate(2009, 10, 23));
		assertEquals(2, appointments.asMap().size());
	}

	@Test
	@Transactional
	public void createAppointment() {
		AppointmentForm form = new AppointmentForm();
		form.setDate(new LocalDate(2009, 12, 29));
		form.setTime(new LocalTime(8, 0, 0, 0));
		form.setNotes("Checkup");
		form.setPatient(1L);
		this.appointmentBook.addAppointment(form);
		DoctorAppointments appointments = this.appointmentBook.getAppointmentsForDay(new LocalDate(2009, 12, 29));
		assertEquals(1, appointments.asMap().size());
		Appointment a = appointments.getAppointments("Dwight Howard").iterator().next();
		assertEquals(new DateTime(2009, 12, 29, 8, 0, 0, 0), a.getDateTime());
		assertEquals("Macy", a.getPatient());
		assertEquals("Keith Donald", a.getClient());
		assertEquals("1-205-333-5555", a.getClientPhone());
	}

}
