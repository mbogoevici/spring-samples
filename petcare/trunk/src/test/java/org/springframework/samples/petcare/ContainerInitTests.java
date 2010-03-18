package org.springframework.samples.petcare;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.samples.petcare.appointments.integration.AppointmentMessageGateway;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath:/META-INF/spring/shared/shared.xml",
	"classpath:/META-INF/spring/petcare-servlet/petcare-servlet.xml"
})
public class ContainerInitTests {

	@Inject
	private ApplicationContext context;
	
	@Test
	public void testOk() {
		AppointmentMessageGateway gateway = context.getBean("appointmentMessageGateway", AppointmentMessageGateway.class);
		System.out.println(gateway);
	}
}
