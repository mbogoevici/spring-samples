package org.springframework.samples.petcare.appointments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

public class AppointmentCalendarTest {

	AppointmentCalendar calendar;
	
	@Before
	public void setUp() {
		LocalDate day = new LocalDate(2010, 2, 16);
		calendar = new AppointmentCalendar(day);
		List<DoctorReference> doctors = new ArrayList<DoctorReference>();
		doctors.add(new DoctorReference(1L, "Dwight Howard"));
		doctors.add(new DoctorReference(2L, "Rashad Lewis"));
		calendar.setDoctors(doctors);
		
		Appointment a1 = new Appointment();
		a1.setPatient("Macy");
		a1.setStartTime(day.toDateTime(new LocalTime(8, 0)));
		a1.setClient("Keith Donald");
		a1.setClientPhone("123456789");
		a1.setReason("Teeth cleaning");
		
		Appointment a2 = new Appointment();
		a2.setPatient("Lil Jerry");
		a2.setStartTime(day.toDateTime(new LocalTime(13, 0)));
		a2.setClient("Keri Donald");
		a2.setClientPhone("123456789");
		a2.setReason("Annual checkup");

		Appointment a3 = new Appointment();
		a3.setPatient("Max");
		a3.setStartTime(day.toDateTime(new LocalTime(16, 0)));
		a3.setClient("Aldis Donald");
		a3.setClientPhone("123456789");
		a3.setReason("Annual checkup");

		Appointment a4 = new Appointment();
		a4.setPatient("Beau");
		a4.setStartTime(day.toDateTime(new LocalTime(11, 0)));
		a4.setClient("Scot Donald");
		a4.setClientPhone("123456789");
		a4.setReason("Annual checkup");

		calendar.addAppointment(1L, a1);
		calendar.addAppointment(1L, a2);
		calendar.addAppointment(2L, a3);
		calendar.addAppointment(2L, a4);
	}
	
	@Test
	public void getBlocks() {
		List<Block> blocks = calendar.getBlocks();
		assertEquals(9, blocks.size());		
		assertEquals(new LocalTime(8, 0), blocks.get(0).getTime());
		assertEquals(new LocalTime(9, 0), blocks.get(1).getTime());
		assertEquals(new LocalTime(10, 0), blocks.get(2).getTime());
		assertEquals(new LocalTime(11, 0), blocks.get(3).getTime());
		assertEquals(new LocalTime(12, 0), blocks.get(4).getTime());
		assertEquals(new LocalTime(13, 0), blocks.get(5).getTime());
		assertEquals(new LocalTime(14, 0), blocks.get(6).getTime());
		assertEquals(new LocalTime(15, 0), blocks.get(7).getTime());
		assertEquals(new LocalTime(16, 0), blocks.get(8).getTime());
	}
	
	@Test
	public void getDoctors() {
		List<DoctorReference> doctors = calendar.getDoctors();
		assertEquals(1L, (long) doctors.get(0).getId());
		assertEquals("Dwight Howard", doctors.get(0).getName());
		assertEquals(2L, (long) doctors.get(1).getId());
		assertEquals("Rashad Lewis", doctors.get(1).getName());
	}
	
	@Test
	public void getAppointment() {
		Appointment appointment = calendar.getAppointments().get(0).get(0);
		assertNotNull(appointment);
		assertEquals("Macy", appointment.getPatient());

		appointment = calendar.getAppointments().get(1).get(0);
		assertNull(appointment);

		appointment = calendar.getAppointments().get(5).get(0);
		assertNotNull(appointment);
		assertEquals("Lil Jerry", appointment.getPatient());

		appointment = calendar.getAppointments().get(5).get(0);
		assertNotNull(appointment);
		assertEquals("Lil Jerry", appointment.getPatient());

		appointment = calendar.getAppointments().get(8).get(1);
		assertNotNull(appointment);
		assertEquals("Max", appointment.getPatient());

		appointment = calendar.getAppointments().get(3).get(1);
		assertNotNull(appointment);
		assertEquals("Beau", appointment.getPatient());
	}
}
