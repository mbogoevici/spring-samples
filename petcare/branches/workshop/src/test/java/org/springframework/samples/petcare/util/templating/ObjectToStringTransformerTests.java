package org.springframework.samples.petcare.util.templating;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.samples.petcare.appointments.Appointment;
import org.springframework.samples.petcare.util.templating.ObjectToStringTransformer;

public class ObjectToStringTransformerTests {

	private ObjectToStringTransformer transformer = new ObjectToStringTransformer();

	@Test
	public void testGenerateDoctorEmail() {
		transformer.setTemplate(new ClassPathResource("org/springframework/samples/petcare/appointments/mail/new-appointment-mail.st"));
		Appointment appointment = new Appointment();
		appointment.setPatient("Macy");
		appointment.setClient("Keith Donald");
		appointment.setClientPhone("1234567890");
		appointment.setReason("Checkup");
		Message<Appointment> message = MessageBuilder.withPayload(appointment).build();
		Message<?> result = transformer.transform(message);
		System.out.println(result.getPayload());
	}
}
