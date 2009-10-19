package org.springframework.samples.petclinic.appointments;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/org/springframework/samples/petclinic/system-test-config.xml")
public class AppointmentBookTests {

	@Autowired
	private AppointmentBook appointmentBook;

	private SimpleJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Test
	@Transactional
	public void testGetAppointmentsForToday() {

	}

	@Test
	public void testGetAppointForAParticularDay() {

	}

	@Test
	@Transactional
	public void testCreateAppointment() {

	}

}
