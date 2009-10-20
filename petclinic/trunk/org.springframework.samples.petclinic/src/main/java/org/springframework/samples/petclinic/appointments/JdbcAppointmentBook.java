package org.springframework.samples.petclinic.appointments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JdbcAppointmentBook implements AppointmentBook {

	private SimpleJdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcAppointmentBook(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	public DoctorAppointments getAppointmentsForDay(LocalDate day) {
		Date startOfDay = day.toDateTimeAtStartOfDay().toDate();
		Date endOfDay = day.plusDays(1).toDateTimeAtStartOfDay().toDate();
		List<Appointment> appointments = this.jdbcTemplate.query(FOR_DAY,
				new AppointmentRowMapper(), startOfDay, endOfDay);
		return new DoctorAppointments(appointments);
	}

	public void addAppointment(AppointmentForm appointment) {
		this.jdbcTemplate.update("insert into Appointment (dateTime, notes, patientId) values (?, ?, ?)",
				appointment.getDate().toDateTime(appointment.getTime()).toDate(), appointment.getNotes(), appointment.getPatient());
	}

	// internal helpers
	
	private static class AppointmentRowMapper implements RowMapper<Appointment> {
		public Appointment mapRow(ResultSet rs, int row) throws SQLException {
			Appointment a = new Appointment();
			a.setDateTime(new DateTime(rs.getTimestamp("DATETIME")));
			a.setDoctor(rs.getString("DOCTOR"));
			a.setClient(rs.getString("CLIENT"));
			a.setClientPhone(rs.getString("CLIENTPHONE"));
			a.setPatient(rs.getString("PATIENT"));
			a.setNotes(rs.getString("NOTES"));
			return a;
		}
	}

	private static final String FOR_DAY = "select a.dateTime, (d.firstName || ' ' || d.lastName) as doctor, (c.firstName || ' ' || c.lastName) as client, c.phone as clientPhone, p.name as patient, a.notes "
			+ "from Appointment a, Doctor d, Client c, Patient p "
			+ "where "
			+ "a.dateTime between ? and ? and "
			+ "a.patientId = p.id and "
			+ "p.clientId = c.id and "
			+ "c.doctorId = d.id "
			+ "order by doctor, dateTime";
}