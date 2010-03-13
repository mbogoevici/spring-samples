package org.springframework.samples.petcare.appointments;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.channel.SubscribableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageDeliveryException;
import org.springframework.integration.message.MessageHandler;
import org.springframework.integration.message.MessageHandlingException;
import org.springframework.integration.message.MessageRejectedException;
import org.springframework.integration.selector.MessageSelector;
import org.springframework.stereotype.Component;

@Component
public class AppointmentCalendarMessageStoreImpl implements AppointmentCalendarMessageStore, MessageHandler, InitializingBean, DisposableBean {

	private final SubscribableChannel messageChannel;

	private final AppointmentMessageSelector messageSelector = new AppointmentMessageSelector();
	
	private final PollableChannel pollableChannel = new QueueChannel();
	
	@Autowired
	public AppointmentCalendarMessageStoreImpl(@Qualifier("messageChannel") SubscribableChannel messageChannel) {
		this.messageChannel = messageChannel;
	}

	// Lifecycle callbacks
	
	public void afterPropertiesSet() throws Exception {
		messageChannel.subscribe(this);
	}

	public void destroy() throws Exception {
		messageChannel.unsubscribe(this);
	}	

	// implementing MessageHandler
	
	public void handleMessage(Message<?> message) throws MessageRejectedException, MessageHandlingException, MessageDeliveryException {
		if (messageSelector.accept(message)) {
			pollableChannel.send(message);
		}
	}

	// implementing AppointmentCalendarMessageStore
	
	public void setDay(LocalDate day) {
		messageSelector.setDay(day);
	}
	
	public List<Message<?>> pollMessages() {
		return pollableChannel.clear();
	}
	
	// internal helpers
	
	private static class AppointmentMessageSelector implements MessageSelector {

		private LocalDate day;
		
		public void setDay(LocalDate day) {
			this.day = day;
		}

		public boolean accept(Message<?> message) {
			if (message.getHeaders().get("element").equals("appointmentCalendar")) {
				Appointment appointment = (Appointment) message.getPayload();
				if (appointment.getDateTime().toLocalDate().equals(day)) {
					return true;
				}
			}
			return false;
		}
	}

}
