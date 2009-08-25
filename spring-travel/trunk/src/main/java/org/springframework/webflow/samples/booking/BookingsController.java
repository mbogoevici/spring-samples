package org.springframework.webflow.samples.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookingsController {

    private TravelService travelService;

    @Autowired
    public BookingsController(TravelService travelService) {
	this.travelService = travelService;
    }

    @RequestMapping(value = "/{userName}/bookings", method = RequestMethod.GET)
    public void getDashboard(@PathVariable("userName") String userName, Model model) {
	List<Booking> booking = travelService.findBookings(userName);
	model.addAttribute(booking);
    }

    @RequestMapping(value = "/{userName}/bookings/{id}", method = RequestMethod.DELETE)
    public String deleteBooking(@PathVariable("id") Long id) {
	travelService.cancelBooking(id);
	return "redirect:/dashboard";
    }
}
