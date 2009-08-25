package org.springframework.webflow.samples.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HotelsController {

    private TravelService travelService;

    @Autowired
    public HotelsController(TravelService travelService) {
	this.travelService = travelService;
    }

    @RequestMapping("/hotels")
    public void getHotels(SearchCriteria criteria, Model model) {
	List<Hotel> hotels = travelService.findHotels(criteria);
	model.addAttribute(hotels);
    }

    @RequestMapping("/hotels/{id}")
    public String getHotel(@PathVariable("id") Long id, Model model) {
	model.addAttribute(travelService.findHotelById(id));
	return "hotel";
    }

    @RequestMapping(value = "/hotels/new", method = RequestMethod.POST)
    public String addHotel(Hotel hotel) {
	hotel = travelService.addHotel(hotel);
	return "redirect:/hotels/" + hotel.getId();
    }

}
