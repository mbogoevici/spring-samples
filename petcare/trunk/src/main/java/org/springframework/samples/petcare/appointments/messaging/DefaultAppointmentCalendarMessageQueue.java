package org.springframework.samples.petcare.appointments.messaging;

import java.io.Serializable;
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
import org.springframework.integration.channel.SubscribableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageDeliveryException;
import org.springframework.integration.message.MessageHandler;
import org.springframework.integration.message.MessageHandlingException;
import org.springframework.integration.message.MessageRejectedException;
import org.springframework.samples.petcare.appointments.Appointment;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session")
public class DefaultAppointmentCalendarMessageQueue implements AppointmentCalendarMessageQueue, MessageHandler, InitializingBean, DisposableBean {

	private final SubscribableChannel appointmentsChannel;

	private final AppointmentCalendarMessageQueueDelegate delegate = new AppointmentCalendarMessageQueueDelegate();
	
	@Inject
	public DefaultAppointmentCalendarMessageQueue(@Named("appointmentsChannel") SubscribableChannel appointmentsChannel) {
		this.appointmentsChannel = appointmentsChannel;
	}

	// Lifecycle callbacks
	
	public void afterPropertiesSet() throws Exception {
		appointmentsChannel.subscribe(this);
	}

	public void destroy() throws Exception {
		appointmentsChannel.unsubscribe(this);
	}	

	// implementing MessageHandler
	
	public void handleMessage(Message<?> message) throws MessageRejectedException, MessageHandlingException, MessageDeliveryException {
		delegate.add((AppointmentMessage) message.getPayload());
	}

	// implementing AppointmentCalendarMessageStore
	
	public void setDay(LocalDate day) {
		delegate.setDay(day);
	}
	
	public Set<AppointmentMessage> pollMessages() {
		return delegate.pollMessages();
	}
	
	// internal helpers
	
	@SuppressWarnings("serial")
	private static class AppointmentCalendarMessageQueueDelegate implements AppointmentCalendarMessageQueue, Serializable {

		private final MessageSelector selector = new MessageSelector();

		private final BlockingQueue<AppointmentMessage> queue = new LinkedBlockingQueue<AppointmentMessage>();
		
		boolean add(AppointmentMessage message) {
			if (selector.accept(message)) {
				queue.add(message);
				return true;
			}
			return false;
		}
		
		public void setDay(LocalDate day) {
			selector.setDay(day);
		}
		
		public Set<AppointmentMessage> pollMessages() {
			Set<AppointmentMessage> messages = new LinkedHashSet<AppointmentMessage>();
			queue.drainTo(messages);
			return messages;
		}
		
		private static class MessageSelector implements Serializable {

			private LocalDate day;
			
			public void setDay(LocalDate day) {
				this.day = day;
			}

			public boolean accept(AppointmentMessage message) {
				Appointment appointment = message.getAppointment();
				return appointment.getDateTime().toLocalDate().equals(day);
			}
		}

	}
	
}
