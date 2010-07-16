package org.springframework.samples.mvc.form;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/form")
public class FormController {

	@RequestMapping(method=RequestMethod.GET)
	public void form(HttpSession session, Model model) {
		FormBean form = (FormBean) session.getAttribute("form");
		if (form == null) {
			// form has not yet been submitted; export a new instance
			model.addAttribute(new FormBean());
		} else {
			// we already submitted the form; export the cached instance
			model.addAttribute(form);
			exportSubmitSuccessMessage(session, model);			
		}
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(FormBean form, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return null;
		}
		// simply store form bean in the session for demo purposes, typically you would save form bean values to a db
		session.setAttribute("form", form);
		// cache a success message for rendering on the next request
		session.setAttribute("formSubmitSuccessMessage", "Form submitted successfully.  Bound FormBean " + form);
		// redirect back to the form to render the success message along with newly bound values
		return "redirect:/form";
	}
	
	private void exportSubmitSuccessMessage(HttpSession session, Model model) {
		String message = (String) session.getAttribute("formSubmitSuccessMessage");
		if (message != null) {
			model.addAttribute("message", message);
			// remove the success message from the session so the user only sees it once (flash message semantics)
			session.removeAttribute("formSubmitSuccessMessage");
		}
	}
	
}
