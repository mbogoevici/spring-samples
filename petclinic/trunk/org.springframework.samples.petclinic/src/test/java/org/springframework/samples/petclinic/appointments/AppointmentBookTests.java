package org.springframework.samples.petclinic.appointments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/org/springframework/samples/petclinic/system-test-config.xml")
public class AppointmentBookTests {

	@Autowired
	private AppointmentBook appointmentBook;
	
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	@Autowired
	public void createSimleJdbcTemplate(DataSource dataSource){
		simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	
	@Test
	@Transactional
	public void testSaveAppointment() throws Exception {
		Appointment appointment = new Appointment("Agim","+4971170000","Kitty","vip cat",today());
		Long id = appointmentBook.createAppointment(appointment);
		assertNotNull(id);
		
		Map<String, Object> result = simpleJdbcTemplate.queryForMap("select * from Appointment where id = ?" , id);
		assertEquals("Agim",result.get("owner"));
		assertEquals("+4971170000",result.get("ownerPhone"));
		assertEquals("Kitty",result.get("pet"));
		assertEquals("vip cat",result.get("notes"));
		assertEquals(today(),result.get("dateTime"));
	}
	
	
	@Test
	@Transactional
	public void testGetAppointmentsForToday() throws Exception {
		String insertSql = "insert into Appointment (owner, ownerPhone,pet,notes,dateTime) values (?, ?, ?, ?, ?)";
		simpleJdbcTemplate.update(insertSql, "Agim","+4971170000","Bela","vip cat",today());
		simpleJdbcTemplate.update(insertSql, "Keith","+1300100","Taylor","test Chuck",today());
		
		Appointments appointments = appointmentBook.getAppointmentsForToday();
		
		List<Appointment> allAppointmentsBela = appointments.getAllByVet().get("Bela");
		assertNotNull(allAppointmentsBela);
		assertEquals(1, allAppointmentsBela.size());

		List<Appointment> allAppointmentsTaylor = appointments.getAllByVet().get("Bela");
		assertNotNull(allAppointmentsTaylor);
		assertEquals(1, allAppointmentsTaylor.size());
	}
	
	
	@Test
	public void testGetAppointForAParticularDay() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.GERMANY);
		Date date = dateFormat.parse("2009-06-01");
		
		Appointments appointments = appointmentBook.getAppointmentsForDay(date);
		List<Appointment> allAppointments = appointments.getAllByVet().get("Kitty");
		assertNotNull(allAppointments);
		assertEquals(1, allAppointments.size());
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
