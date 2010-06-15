package org.springframework.samples.petcare.appointments;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.samples.petcare.appointments.integration.AppointmentMessageGateway;
import org.springframework.samples.petcare.appointments.integration.DefaultAppointmentService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class JdbcAppointmentServiceTest {

	private EmbeddedDatabase database;

	private AppointmentMessageGateway gateway;
	
	private DefaultAppointmentService appointmentService;

	private PlatformTransactionManager transactionManager;
	
	private JdbcTemplate template;
	
	@Before
	public void setUp() {
		database = new EmbeddedDatabaseBuilder().
			setType(EmbeddedDatabaseType.H2).
			addScript("schema.sql").
			addScript("data.sql").build();
		template = new JdbcTemplate(database);
		gateway = new AppointmentMessageGateway() {
			public void publish(AppointmentMessage message) {
			}
		};
		appointmentService = new DefaultAppointmentService(template, gateway);
		transactionManager = new DataSourceTransactionManager(database);
	}

	@After
	public void tearDown() {
		database.shutdown();
	}

	@Test
	public void testGetAppointmentsForDay() {
		AppointmentCalendar calendar = appointmentService.getAppointmentsForDay(new LocalDate(2010, 02, 16));
		assertEquals(new LocalDate(2010, 02, 16), calendar.getDay());
		assertEquals("Macy", calendar.getAppointments().get(0).get(0).getPatient());
		assertEquals("Lil Jerry", calendar.getAppointments().get(8).get(1).getPatient());
	}
	
	@Test
	public void testAddAppointment() {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		NewAppointment appointment = new NewAppointment();
		appointment.setDoctorId(1L);
		appointment.setPatientId(1L);
		appointment.setReason("Checkup");
		appointment.setDateTime(new LocalDate().toDateTime(new LocalTime(8, 0)).getMillis());
		long id = appointmentService.addAppointment(appointment);
		assertEquals(3L, id);
		Map<String, Object> row = template.queryForMap("select * from Appointment where id = ?", id);
		assertEquals(new LocalDate().toDateTime(new LocalTime(8, 0)).toDate(), row.get("DATETIME"));
		assertEquals(1L, row.get("DOCTORID"));
		assertEquals(1L, row.get("PATIENTID"));
		assertEquals("Checkup", row.get("REASON"));		
		transactionManager.commit(status);
	}
	
	@Test
	public void testDeleteAppointment() {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());		
		appointmentService.deleteAppointment(1L);
		List<Map<String, Object>> row = template.queryForList("select * from Appointment where id = ?", 1L);
		assertEquals(0, row.size());
		transactionManager.commit(status);
	}

}
