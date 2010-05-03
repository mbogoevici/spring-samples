package org.springframework.samples.travel;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hotels")
public class HotelsController {

	private BookingService bookingService;

	@Inject
	public HotelsController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void index(SearchCriteria searchCriteria, Principal currentUser,
			Model model) {
		if (currentUser != null) {
			List<Booking> booking = bookingService.findBookings(currentUser
					.getName());
			model.addAttribute(booking);
		}
	}

	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String search(SearchCriteria criteria, Model model) {
		List<Hotel> hotels = bookingService.findHotels(criteria);
		model.addAttribute(hotels);
		return "hotels/search";
	}

	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public Hotel show(@PathVariable Long id) {
		return bookingService.findHotelById(id);
	}

	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String deleteBooking(@PathVariable Long id) {
		bookingService.cancelBooking(id);
		return "redirect:index";
	}

}
