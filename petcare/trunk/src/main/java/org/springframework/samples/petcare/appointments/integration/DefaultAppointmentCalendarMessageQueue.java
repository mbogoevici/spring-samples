package org.springframework.samples.petcare.appointments.integration;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.SubscribableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageDeliveryException;
import org.springframework.integration.message.MessageHandler;
import org.springframework.integration.message.MessageHandlingException;
import org.springframework.integration.message.MessageRejectedException;
import org.springframework.samples.petcare.appointments.Appointment;
import org.springframework.samples.petcare.appointments.AppointmentCalendarMessageQueue;
import org.springframework.samples.petcare.appointments.AppointmentMessage;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session")
public class DefaultAppointmentCalendarMessageQueue implements AppointmentCalendarMessageQueue, MessageHandler, InitializingBean, DisposableBean {

	private final SubscribableChannel appointmentChannel;

	private final AppointmentCalendarMessageQueueDelegate delegate = new AppointmentCalendarMessageQueueDelegate();
	
	@Inject
	public DefaultAppointmentCalendarMessageQueue(@Named("appointmentChannel") SubscribableChannel appointmentChannel) {
		this.appointmentChannel = appointmentChannel;
	}

	// Lifecycle callbacks
	
	public void afterPropertiesSet() throws Exception {
		appointmentChannel.subscribe(this);
	}

	public void destroy() throws Exception {
		appointmentChannel.unsubscribe(this);
	}	

	// implementing MessageHandler
	
	public void handleMessage(Message<?> message) throws MessageRejectedException, MessageHandlingException, MessageDeliveryException {
		delegate.handleMessage((AppointmentMessage) message.getPayload());
	}

	// implementing AppointmentCalendarMessageQueue
	
	public void setDay(LocalDate day) {
		delegate.setDay(day);
	}
	
	public Set<AppointmentMessage> pollMessages() {
		return delegate.pollMessages();
	}
	
	// internal helpers
	
	private static class AppointmentCalendarMessageQueueDelegate implements AppointmentCalendarMessageQueue {

		private final MessageSelector selector = new MessageSelector();

		private final BlockingQueue<AppointmentMessage> queue = new LinkedBlockingQueue<AppointmentMessage>();
		
		@ServiceActivator(inputChannel="appointmentChannel")
		public void handleMessage(AppointmentMessage message) {
			if (selector.accepts(message)) {
				queue.add(message);
			}
		}
		
		public void setDay(LocalDate day) {
			selector.setDay(day);
		}
		
		public Set<AppointmentMessage> pollMessages() {
			Set<AppointmentMessage> messages = new LinkedHashSet<AppointmentMessage>();
			queue.drainTo(messages);
			return messages;
		}
		
		private static class MessageSelector {

			private LocalDate day;
			
			public void setDay(LocalDate day) {
				this.day = day;
			}

			public boolean accepts(AppointmentMessage message) {
				Appointment appointment = message.getAppointment();
				return appointment.getDateTime().toLocalDate().equals(day);
			}
		}
		
		public String toString() {
			return "[AppointmentCalendarMessageQueue size = " + queue.size() + "]";
		}

	}
	
	public String toString() {
		return delegate.toString();
	}
}
