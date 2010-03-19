package org.springframework.samples.petcare.appointments.integration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.samples.petcare.appointments.Appointment;
import org.springframework.samples.petcare.appointments.AppointmentCalendar;
import org.springframework.samples.petcare.appointments.AppointmentMessage;
import org.springframework.samples.petcare.appointments.AppointmentService;
import org.springframework.samples.petcare.appointments.NewAppointment;
import org.springframework.samples.petcare.appointments.AppointmentMessage.MessageType;
import org.springframework.samples.petcare.util.EntityReference;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class DefaultAppointmentService implements AppointmentService {

	private final JdbcTemplate jdbcTemplate;
	
	private final AppointmentMessageGateway messageGateway;

	@Inject
	public DefaultAppointmentService(JdbcTemplate jdbcTemplate, AppointmentMessageGateway messageGateway) {
		this.jdbcTemplate = jdbcTemplate;
		this.messageGateway = messageGateway;
	}

	public AppointmentCalendar getAppointmentsForDay(LocalDate day) {
		AppointmentCalendar calendar = new AppointmentCalendar(day);
		calendar.setDoctors(jdbcTemplate.query(DOCTORS, new RowMapper<EntityReference>() {
			public EntityReference mapRow(ResultSet rs, int row) throws SQLException {
				return new EntityReference(rs.getLong("ID"), rs.getString("DOCTOR"));
			}
		}));
		jdbcTemplate.query(APPOINTMENTS_FOR_DAY, new AppointmentCalendarPopulator(calendar), calendar.getStartOfDay(), calendar.getEndOfDay());
		return calendar;
	}

	public Long addAppointment(NewAppointment newAppointment) {
		jdbcTemplate.update("insert into Appointment (dateTime, patientId, doctorId, reason) values (?, ?, ?, ?)", 
				new Date(newAppointment.getDateTime()), newAppointment.getPatientId(), newAppointment.getDoctorId(), newAppointment.getReason());
		Long id = jdbcTemplate.queryForLong("call identity()");
		messageGateway.publish(new AppointmentMessage(MessageType.APPOINTMENT_ADDED, getAppointment(id)));
		return id;
	}
	
	public void deleteAppointment(Long id) {
		Appointment appointment = getAppointment(id);
		jdbcTemplate.update("delete from Appointment where id = ?", appointment.getId());
		messageGateway.publish(new AppointmentMessage(MessageType.APPOINTMENT_DELETED, appointment));
	}

	// internal helpers
	
	private Appointment getAppointment(Long id) {
		return this.jdbcTemplate.queryForObject(APPOINTMENT_BY_ID, new AppointmentRowMapper(), id);
	}
	
	private static final String DOCTORS = "select id, (firstName || ' ' || lastName) as doctor from Doctor";

	private static final String APPOINTMENTS_FOR_DAY = "select a.id, a.dateTime, a.doctorId, p.name as patient, (c.firstName || ' ' || c.lastName) as client, c.phone as clientPhone, a.reason "
			+ "from Appointment a, Doctor d, Patient p, Client c "
			+ "where "
			+ "a.dateTime between ? and ? and a.patientId = p.id and p.clientId = c.id and a.doctorId = d.id";
	
	private static final String APPOINTMENT_BY_ID = "select a.id, a.dateTime, a.doctorId, p.name as patient, (c.firstName || ' ' || c.lastName) as client, c.phone as clientPhone, a.reason "
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
			calendar.addAppointment(rowMapper.mapRow(rs, -1));
		}
	}
	
	private static class AppointmentRowMapper implements RowMapper<Appointment> {

		public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Appointment a = new Appointment();
			a.setId(rs.getLong("ID"));
			a.setDateTime(new DateTime(rs.getTimestamp("DATETIME")));
			a.setDoctorId(rs.getLong("DOCTORID"));
			a.setPatient(rs.getString("PATIENT"));
			a.setClient(rs.getString("CLIENT"));
			a.setClientPhone(rs.getString("CLIENTPHONE"));
			a.setReason(rs.getString("REASON"));
			return a;
		}
		
	}

}