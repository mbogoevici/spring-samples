package org.springframework.samples.petcare.appointments.messaging;

import javax.inject.Inject;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.integration.annotation.Transformer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.samples.petcare.appointments.messaging.AppointmentMessage.MessageType;
import org.springframework.samples.petcare.util.templating.StringTemplate;
import org.springframework.samples.petcare.util.templating.StringTemplateFactory;

public class DoctorMailTransformer {
	
	private StringTemplateFactory templateFactory;
	
	private Resource newTemplate = new ClassPathResource("/templates/new-appointment-mail.st");

	private Resource canceledTemplate = new ClassPathResource("/templates/canceled-appointment-mail.st");

	@Inject
	public DoctorMailTransformer(JdbcTemplate jdbcTemplate, StringTemplateFactory templateFactory) {
		this.templateFactory = templateFactory;
	}

	@Transformer
	public MailMessage transform(AppointmentMessage message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("noreply@petcare.com");
		mailMessage.setTo("name@emailAddress.com");
		StringTemplate textTemplate;
		if (message.getType().equals(MessageType.APPOINTMENT_ADDED)) {
			mailMessage.setSubject("New appointment added");
			textTemplate = templateFactory.getStringTemplate(newTemplate);
		} else {
			mailMessage.setSubject("Appointment cancelled");			
			textTemplate = templateFactory.getStringTemplate(canceledTemplate);
		}
		textTemplate.setAttribute("appointment", message.getAppointment());
		mailMessage.setText(textTemplate.toString());
		return mailMessage;
	}
	
}
