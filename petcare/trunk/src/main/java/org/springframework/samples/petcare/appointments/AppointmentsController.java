package org.springframework.samples.petcare.appointments;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.integration.core.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/appointments")
public class AppointmentsController {

	private final AppointmentService appointmentRepository;

	private AppointmentMessageHandler messageHandler;
	
	@Autowired
	public AppointmentsController(AppointmentService appointmentRepository, AppointmentMessageHandler messageHandler) {
		this.appointmentRepository = appointmentRepository;
		this.messageHandler = messageHandler;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAppointments(Model model) {
		return getAppointmentsForDay(new LocalDate(), model);
	}

	@RequestMapping(method = RequestMethod.GET, params="day")
	public String getAppointmentsForDay(@RequestParam @DateTimeFormat(iso=ISO.DATE) LocalDate day, Model model) {
		model.addAttribute(appointmentRepository.getAppointmentsForDay(day));
		return "appointments";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String createAppointment(NewAppointment appointment) {
		appointmentRepository.addAppointment(appointment);
		return "redirect:/appointments?day=" + appointment.getDay();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void deleteAppointment(@PathVariable Long id, HttpServletResponse response) {
		appointmentRepository.deleteAppointment(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
	@RequestMapping(value="/notifications", method = RequestMethod.GET)
	public @ResponseBody List<Message<?>> pollAppointmentNotifications(@RequestParam @DateTimeFormat(iso=ISO.DATE) LocalDate day) {
		messageHandler.setDay(day);
		return messageHandler.pollMessages();
	}
}
