package org.springframework.webflow.samples.booking;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

    private TravelService travelService;

    @Autowired
    public DashboardController(TravelService travelService) {
	this.travelService = travelService;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public void getDashboard(SearchCriteria searchCriteria, Principal currentUser, Model model) {
	if (currentUser != null) {
	    List<Booking> booking = travelService.findBookings(currentUser.getName());
	    model.addAttribute(booking);
	}
    }
}
