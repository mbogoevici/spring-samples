package org.springframework.samples.petcare.appointments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.samples.petcare.util.ResourceReference;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JdbcAppointmentRepository implements AppointmentRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcAppointmentRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public AppointmentCalendar getAppointmentsForDay(LocalDate day) {
		Date startOfDay = day.toDateTimeAtStartOfDay().toDate();
		Date endOfDay = day.plusDays(1).toDateTimeAtStartOfDay().toDate();
		AppointmentCalendar calendar = new AppointmentCalendar(day);
		calendar.setDoctors(jdbcTemplate.query(DOCTORS, new RowMapper<ResourceReference>() {
			public ResourceReference mapRow(ResultSet rs, int row) throws SQLException {
				return new ResourceReference(rs.getLong("ID"), rs.getString("DOCTOR"));
			}
		}));
		jdbcTemplate.query(APPOINTMENTS_FOR_DAY, new AppointmentCalendarPopulator(calendar), startOfDay, endOfDay);
		return calendar;
	}

	public void addAppointment(NewAppointment appointment) {
		DateTime startTime = appointment.getDay().toDateTime(appointment.getTime());
		jdbcTemplate.update("insert into Appointment (startTime, endTime, reason, patientId) values (?, ?, ?)", startTime, startTime.plusHours(1), appointment.getReason(), appointment.getPatientId());
	}

	// internal helpers

	private static final String DOCTORS = "select id, (firstName || ' ' || lastName) as doctor from Doctor";

	private static final String APPOINTMENTS_FOR_DAY = "select a.startTime, a.endTime, d.id as doctorId, p.name as patient, (c.firstName || ' ' || c.lastName) as client, c.phone as clientPhone, a.reason "
			+ "from Appointment a, Doctor d, Patient p, Client c "
			+ "where "
			+ "a.startTime between ? and ? and "
			+ "a.patientId = p.id and p.clientId = c.id and p.doctorId = d.id";

	private static class AppointmentCalendarPopulator implements RowCallbackHandler {

		private AppointmentCalendar calendar;

		public AppointmentCalendarPopulator(AppointmentCalendar calendar) {
			this.calendar = calendar;
		}

		public void processRow(ResultSet rs) throws SQLException, DataAccessException {
			Appointment a = new Appointment();
			a.setStartTime(new DateTime(rs.getTimestamp("STARTTIME")));
			a.setEndTime(new DateTime(rs.getTimestamp("ENDTIME")));
			a.setPatient(rs.getString("PATIENT"));
			a.setClient(rs.getString("CLIENT"));
			a.setClientPhone(rs.getString("CLIENTPHONE"));
			a.setReason(rs.getString("REASON"));
			calendar.addAppointment(rs.getLong("DOCTORID"), a);
		}
	}

}