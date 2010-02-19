package org.springframework.samples.petcare.appointments;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/appointments")
public class AppointmentsController {

	private final AppointmentRepository appointmentRepository;

	@Autowired
	public AppointmentsController(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAppointments(Model model) {
		return getAppointmentsForDay(new LocalDate(), model);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getAppointmentsForDay(@PathVariable @DateTimeFormat(iso=ISO.DATE) LocalDate id, Model model) {
		model.addAttribute(appointmentRepository.getAppointmentsForDay(id));
		return "appointments";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String createAppointment(NewAppointment appointment) {
		appointmentRepository.createAppointment(appointment);
		return "redirect:/appointments/" + appointment.getDay();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void deleteAppointment(@PathVariable Long id, HttpServletResponse response) {
		appointmentRepository.deleteAppointment(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}
