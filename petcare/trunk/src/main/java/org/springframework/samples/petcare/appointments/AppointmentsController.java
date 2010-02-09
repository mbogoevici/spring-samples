package org.springframework.samples.petcare.appointments;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/appointments")
public class AppointmentsController {

	private final AppointmentRepository appointmentRepository;

	@Autowired
	public AppointmentsController(AppointmentRepository appointmentBook) {
		this.appointmentRepository = appointmentBook;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAppointments(Model model) {
		return getAppointmentsForDay(new LocalDate(), model);
	}

	@RequestMapping(value = "/{day}", method = RequestMethod.GET)
	public String getAppointmentsForDay(@PathVariable @DateTimeFormat(iso=ISO.DATE) LocalDate day, Model model) {
		model.addAttribute(appointmentRepository.getAppointmentsForDay(day));
		return "appointments";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addAppointment(@Valid NewAppointment appointment, BindingResult result) {
		if (result.hasErrors()) {
			return "appointments/new";
		}
		appointmentRepository.addAppointment(appointment);
		return "redirect:/appointments";
	}
}
