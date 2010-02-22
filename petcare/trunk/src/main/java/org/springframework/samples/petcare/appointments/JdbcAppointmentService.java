package org.springframework.samples.petcare.appointments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.samples.petcare.util.ResourceReference;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class JdbcAppointmentService implements AppointmentService {

	private final JdbcTemplate jdbcTemplate;
	
	private MessageChannel messageChannel;

	@Autowired
	public JdbcAppointmentService(DataSource dataSource, @Qualifier("notifications") MessageChannel messageChannel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.messageChannel = messageChannel;
	}

	public AppointmentCalendar getAppointmentsForDay(LocalDate day) {
		AppointmentCalendar calendar = new AppointmentCalendar(day);
		calendar.setDoctors(jdbcTemplate.query(DOCTORS, new RowMapper<ResourceReference>() {
			public ResourceReference mapRow(ResultSet rs, int row) throws SQLException {
				return new ResourceReference(rs.getLong("ID"), rs.getString("DOCTOR"));
			}
		}));
		Date startOfDay = day.toDateTimeAtStartOfDay().toDate();
		Date endOfDay = day.plusDays(1).toDateTimeAtStartOfDay().toDate();		
		jdbcTemplate.query(APPOINTMENTS_FOR_DAY, new AppointmentCalendarPopulator(calendar), startOfDay, endOfDay);
		return calendar;
	}

	public Long addAppointment(NewAppointment newAppointment) {
		DateTime startTime = newAppointment.getDay().toDateTime(newAppointment.getTime());
		jdbcTemplate.update("insert into Appointment (startTime, endTime, patientId, doctorId, reason) values (?, ?, ?, ?, ?)", 
				startTime.toDate(), startTime.plusHours(1).toDate(), newAppointment.getPatientId(), newAppointment.getDoctorId(), newAppointment.getReason());
		Long id = jdbcTemplate.queryForLong("call identity()");
		Message<Appointment> addedMessage = MessageBuilder.withPayload(getAppointment(id)).setHeader("element", "appointmentCalendar").setHeader("type", "appointmentAdded").build();
		messageChannel.send(addedMessage);		
		return id;
	}
	
	public void deleteAppointment(Long id) {
		Appointment appointment = getAppointment(id);
		jdbcTemplate.update("delete from Appointment where id = ?", appointment.getId());
		Message<Appointment> deletedMessage = MessageBuilder.withPayload(appointment).setHeader("element", "appointmentCalendar").setHeader("type", "appointmentDeleted").build();
		messageChannel.send(deletedMessage);
	}

	// internal helpers
	
	private Appointment getAppointment(Long id) {
		return this.jdbcTemplate.queryForObject(APPOINTMENT_BY_ID, new AppointmentRowMapper(), id);
	}
	
	private static final String DOCTORS = "select id, (firstName || ' ' || lastName) as doctor from Doctor";

	private static final String APPOINTMENTS_FOR_DAY = "select a.id, a.startTime, a.endTime, a.doctorId, p.name as patient, (c.firstName || ' ' || c.lastName) as client, c.phone as clientPhone, a.reason "
			+ "from Appointment a, Doctor d, Patient p, Client c "
			+ "where "
			+ "a.startTime between ? and ? and a.patientId = p.id and p.clientId = c.id and a.doctorId = d.id";
	
	private static final String APPOINTMENT_BY_ID = "select a.id, a.startTime, a.endTime, a.doctorId, p.name as patient, (c.firstName || ' ' || c.lastName) as client, c.phone as clientPhone, a.reason "
		+ "from Appointment a, Doctor d, Patient p, Client c "
		+ "where "
		+ "a.id = ? and a.patientId = p.id and p.clientId = c.id and a.doctorId = d.id";

	private static class AppointmentCalendarPopulator implements RowCallbackHandler {

		private AppointmentCalendar calendar;

		private RowMapper<Appointment> rowMapper = new AppointmentRowMapper();
		
		public AppointmentCalendarPopulator(AppointmentCalendar calendar) {
			this.calendar = calendar;
		}

		public void processRow(ResultSet rs) throws SQLException, DataAccessException {
			calendar.addAppointment(rs.getLong("DOCTORID"), rowMapper.mapRow(rs, -1));
		}
	}
	
	private static class AppointmentRowMapper implements RowMapper<Appointment> {

		public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Appointment a = new Appointment();
			a.setId(rs.getLong("ID"));
			a.setStartTime(new DateTime(rs.getTimestamp("STARTTIME")));
			a.setEndTime(new DateTime(rs.getTimestamp("ENDTIME")));
			a.setPatient(rs.getString("PATIENT"));
			a.setClient(rs.getString("CLIENT"));
			a.setClientPhone(rs.getString("CLIENTPHONE"));
			a.setReason(rs.getString("REASON"));
			return a;
		}
		
	}

}