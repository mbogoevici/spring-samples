package org.springframework.samples.mvc.form;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.samples.mvc.flash.FlashMap;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/form")
public class FormController {

	@RequestMapping(method=RequestMethod.GET)
	public FormBean form(HttpSession session) {
		FormBean form = (FormBean) session.getAttribute("form");
		return form != null ? form : new FormBean();
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@Valid FormBean form, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return null;
		}
		// simply store form bean in the session for demo purposes, typically you would save form bean values to a db
		session.setAttribute("form", form);
		// store a success message for rendering on the next request
		FlashMap.setSuccessMessage("Form submitted successfully.  Bound " + form);
		// redirect back to the form to render the success message along with newly bound values
		return "redirect:/form";
	}
		
}
