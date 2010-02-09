package org.springframework.samples.petcare.notifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notifications")
public class NotificationsController {

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Notification> getNotifications() {
		return new ArrayList<Notification>();
	}
}
