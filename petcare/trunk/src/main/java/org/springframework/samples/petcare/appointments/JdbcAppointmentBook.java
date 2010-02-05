package org.springframework.samples.petcare.appointments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CachingMapDecorator;

@Service
@Transactional
public class JdbcAppointmentBook implements AppointmentBook {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcAppointmentBook(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Map<String, List<Appointment>> getAppointmentsForDay(LocalDate day) {
		Date startOfDay = day.toDateTimeAtStartOfDay().toDate();
		Date endOfDay = day.plusDays(1).toDateTimeAtStartOfDay().toDate();
		return this.jdbcTemplate.query(FOR_DAY, new Date[] { startOfDay, endOfDay }, new AppointmentsExtractor());
	}

	public void addAppointment(AppointmentForm form) {
		this.jdbcTemplate.update("insert into Appointment (dateTime, notes, patientId) values (?, ?, ?)", form
				.getDateTime().toDate(), form.getNotes(), form.getPatient());
	}

	// internal helpers

	private static final String FOR_DAY = "select a.dateTime, (d.firstName || ' ' || d.lastName) as doctor, (c.firstName || ' ' || c.lastName) as client, c.phone as clientPhone, p.name as patient, a.notes "
		+ "from Appointment a, Doctor d, Client c, Patient p "
		+ "where "
		+ "a.dateTime between ? and ? and "
		+ "a.patientId = p.id and p.clientId = c.id and p.doctorId = d.id order by doctor, dateTime";

	private static class AppointmentsExtractor implements ResultSetExtractor<Map<String, List<Appointment>>> {

		public Map<String, List<Appointment>> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<String, List<Appointment>> appointments = createMapOfLists(String.class, Appointment.class);
			while (rs.next()) {
				Appointment a = new Appointment();
				a.setDateTime(new DateTime(rs.getTimestamp("DATETIME")));
				a.setClient(rs.getString("CLIENT"));
				a.setClientPhone(rs.getString("CLIENTPHONE"));
				a.setPatient(rs.getString("PATIENT"));
				a.setNotes(rs.getString("NOTES"));
				appointments.get(rs.getString("DOCTOR")).add(a);
			}
			return appointments;
		}
		
		@SuppressWarnings("serial")
		private static <K, V> Map<K, List<V>> createMapOfLists(Class<K> keyType, Class<V> valueElementType) {
			return new CachingMapDecorator<K, List<V>>() {
				protected List<V> create(K key) {
					return new ArrayList<V>();
				}
			};
		}

	}
}