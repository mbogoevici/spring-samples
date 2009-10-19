package org.springframework.samples.petclinic.appointments;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/appointments")
public class AppointmentsController {

	private AppointmentBook appointmentBook;

	@Autowired
	public AppointmentsController(AppointmentBook appointmentBook) {
		this.appointmentBook = appointmentBook;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Appointments get() {
		return appointmentBook.getAppointmentsForToday();
	}

	@RequestMapping(value = "/{day}", method = RequestMethod.GET)
	public String getForDay(@PathVariable LocalDate day, Model model) {
		Appointments appointments = appointmentBook.getAppointmentsForDay(day);
		model.addAttribute(appointments);
		return "appointments";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public Appointment getNewForm() {
		return new Appointment();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Appointment appointment) {
		appointmentBook.createAppointment(appointment);
		return "redirect:/appointments";
	}
}
