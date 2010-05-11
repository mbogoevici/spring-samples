package org.springframework.samples.petcare.appointments;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/appointments")
public class AppointmentsController {

	private final AppointmentService appointmentService;

	/* A reference to a session-scoped bean */
	private final Provider<AppointmentCalendarMessageQueue> messageQueueProvider;
	
	@Inject
	public AppointmentsController(AppointmentService appointmentService, Provider<AppointmentCalendarMessageQueue> messageQueueProvider) {
		this.appointmentService = appointmentService;
		this.messageQueueProvider = messageQueueProvider;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAppointments(Model model) {
		return getAppointmentsForDay(new LocalDate(), model);
	}

	@RequestMapping(method = RequestMethod.GET, params="day")
	public String getAppointmentsForDay(@RequestParam @DateTimeFormat(iso=ISO.DATE) LocalDate day, Model model) {
		model.addAttribute(appointmentService.getAppointmentsForDay(day));
		return "appointments";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Long addAppointment(@Valid NewAppointment appointment) {
		return appointmentService.addAppointment(appointment);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAppointment(@PathVariable Long id) {
		appointmentService.deleteAppointment(id);
	}
	
	@RequestMapping(value="/messages", method = RequestMethod.GET)
	public @ResponseBody Set<AppointmentMessage> pollMessages(@RequestParam @DateTimeFormat(iso=ISO.DATE) LocalDate day) {
		AppointmentCalendarMessageQueue messageQueue = messageQueueProvider.get();
		messageQueue.setDay(day);
		return messageQueue.pollMessages();
	}
}
