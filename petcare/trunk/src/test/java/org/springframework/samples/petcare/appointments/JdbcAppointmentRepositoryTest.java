package org.springframework.samples.petcare.appointments;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class JdbcAppointmentRepositoryTest {

	private EmbeddedDatabase database;

	private JdbcAppointmentRepository appointmentRepository;

	@Before
	public void setUp() {
		database = new EmbeddedDatabaseBuilder().
			setType(EmbeddedDatabaseType.H2).
			addScript("schema.sql").
			addScript("data.sql").build();
		appointmentRepository = new JdbcAppointmentRepository(database);
	}

	@After
	public void tearDown() {
		database.shutdown();
	}

	@Test
	public void testGetAppointmentsForDay() {
		AppointmentCalendar calendar = appointmentRepository.getAppointmentsForDay(new LocalDate(2010, 02, 16));
		assertEquals(new LocalDate(2010, 02, 16), calendar.getDay());
		assertEquals("Macy", calendar.getAppointments().get(0).get(0).getPatient());
		assertEquals("Lil Jerry", calendar.getAppointments().get(8).get(1).getPatient());
	}

}
