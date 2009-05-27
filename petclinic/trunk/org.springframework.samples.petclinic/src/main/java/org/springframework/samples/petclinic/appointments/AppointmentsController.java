package org.springframework.samples.petclinic.appointments;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.util.ExternalContext;
import org.springframework.stereotype.Controller;
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

	// /appointments
	@RequestMapping(method = RequestMethod.GET)
	public Appointments get() {
		return appointmentBook.getAppointmentsForToday();
	}

	// /appointments/2009-5-27
	@RequestMapping(value="/{day}", method = RequestMethod.GET)
	public void getForDay(@PathVariable Date day, ExternalContext context) {
		Appointments appts = appointmentBook.getAppointmentsForDay(day);
		context.getModel().addAttribute(appts);
		context.selectView("appointments");
		if (context.isAjaxRequest()) {
			//could activate a ViewHelper for component associated with main
			context.renderFragment("main");
		}
	}

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public AppointmentForm getNewForm() {
		return new AppointmentForm();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String post(AppointmentForm form) {
		appointmentBook.createAppointment(form);
		return "redirect:/appointments";
	}
}
