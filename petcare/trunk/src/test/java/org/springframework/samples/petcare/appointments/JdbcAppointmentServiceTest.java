package org.springframework.samples.petcare.appointments;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.SubscribableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageDeliveryException;
import org.springframework.integration.message.MessageHandler;
import org.springframework.integration.message.MessageHandlingException;
import org.springframework.integration.message.MessageRejectedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class JdbcAppointmentServiceTest {

	private EmbeddedDatabase database;

	private SubscribableChannel testNotificationChannel;
	
	private JdbcAppointmentService appointmentService;

	private PlatformTransactionManager transactionManager;
	
	private JdbcTemplate template;
	
	@Before
	public void setUp() {
		database = new EmbeddedDatabaseBuilder().
			setType(EmbeddedDatabaseType.H2).
			addScript("schema.sql").
			addScript("data.sql").build();
		testNotificationChannel = new PublishSubscribeChannel();
		appointmentService = new JdbcAppointmentService(database, testNotificationChannel);
		transactionManager = new DataSourceTransactionManager(database);
		template = new JdbcTemplate(database);
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
		testNotificationChannel.subscribe(new MessageHandler() {
			public void handleMessage(Message<?> message)
					throws MessageRejectedException, MessageHandlingException,
					MessageDeliveryException {
				assertEquals("appointmentCalendar", message.getHeaders().get("element"));
				assertEquals("appointmentAdded", message.getHeaders().get("type"));
				Appointment a = (Appointment) message.getPayload();
				assertEquals("Macy", a.getPatient());
			}
		});
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
		testNotificationChannel.subscribe(new MessageHandler() {
			public void handleMessage(Message<?> message)
					throws MessageRejectedException, MessageHandlingException,
					MessageDeliveryException {
				assertEquals("appointmentCalendar", message.getHeaders().get("element"));
				assertEquals("appointmentDeleted", message.getHeaders().get("type"));
				Appointment a = (Appointment) message.getPayload();
				assertEquals("Macy", a.getPatient());
			}
		});
		appointmentService.deleteAppointment(1L);
		List<Map<String, Object>> row = template.queryForList("select * from Appointment where id = ?", 1L);
		assertEquals(0, row.size());
		transactionManager.commit(status);
	}

}
