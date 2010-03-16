package org.springframework.samples.petcare.appointments.messaging;

import javax.inject.Inject;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.integration.annotation.Transformer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.samples.petcare.util.templating.StringTemplate;
import org.springframework.samples.petcare.util.templating.StringTemplateFactory;

public class DoctorMailTransformer {
	
	private StringTemplateFactory templateFactory;
	
	private Resource mailTemplateResource = new ClassPathResource("/templates/new-appointment-mail.st");
	
	@Inject
	public DoctorMailTransformer(JdbcTemplate jdbcTemplate, StringTemplateFactory templateFactory) {
		this.templateFactory = templateFactory;
	}

	@Transformer
	public MailMessage transform(AppointmentMessage message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("noreply@petcare.com");
		mailMessage.setTo("name@emailAddress.com");
		mailMessage.setSubject("New appointment added");
		StringTemplate mailTemplate = templateFactory.getStringTemplate(mailTemplateResource);
		mailTemplate.setAttribute("appointment", message.getAppointment());
		mailMessage.setText(mailTemplate.toString());
		return mailMessage;
	}
	
}
