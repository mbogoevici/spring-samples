package org.springframework.webflow.samples.booking;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PreferencesController {

    private TravelService travelService;

    /**
     * This empty constructor is needed in order for the Spring Security annotations to work as expected
     */
    public PreferencesController() {

    }

    @Autowired
    public PreferencesController(TravelService travelService) {
	this.travelService = travelService;
    }

    @PreAuthorize("!isAnonymous() AND (#username == principal.username OR hasRole('ROLE_ADMIN'))")
    @RequestMapping(value = "/{username}/preferences", method = RequestMethod.GET)
    public String getPreferences(@PathVariable String username, Model model) {
	List<Preference> preferences = travelService.findPreferences(username);
	if (preferences.size() == 0) {
	    Preference pref = new Preference();
	    pref.setId(new Long(1));
	    preferences.add(pref);
	}
	model.addAttribute("preferences", preferences);
	return "preferences";
    }

    @PreAuthorize("!isAnonymous() AND (#username == principal.username OR hasRole('ROLE_ADMIN'))")
    @RequestMapping(value = "/{username}/preferences/{id}", method = RequestMethod.GET)
    public String getPreference(@PathVariable String username, @PathVariable Long id) {
	travelService.findPreferenceById(id);
	return "preferences/view";
    }

    @PreAuthorize("!isAnonymous() AND (#username == principal.username OR hasRole('ROLE_ADMIN'))")
    @RequestMapping(value = "/{username}/preferences/new", method = RequestMethod.POST)
    public String addPreference(Preference preference, Principal currentUser) {
	preference = travelService.addPreference(currentUser.getName(), preference);
	return "redirect:/" + currentUser.getName() + "/preferences/" + preference.getId();
    }

    @PreAuthorize("!isAnonymous() AND (#username == principal.username OR hasRole('ROLE_ADMIN'))")
    @RequestMapping(value = "/{username}/preferences/{id}", method = RequestMethod.PUT)
    public String savePreference(Preference preference, Principal currentUser) {
	travelService.savePreference(preference);
	return "redirect:/" + currentUser.getName() + "/preferences/" + preference.getId();
    }

    @PreAuthorize("!isAnonymous() AND (#username == principal.username OR hasRole('ROLE_ADMIN'))")
    @RequestMapping(value = "/{username}/preferences/{id}", method = RequestMethod.DELETE)
    public String deletePreference(@PathVariable("id") Long id, Principal currentUser) {
	travelService.deletePreference(id);
	return "redirect:/" + currentUser.getName() + "/preferences";
    }
}
