package org.springsource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application welcome page.
 */
@Controller
public class WelcomeController {

	/**
	 * Simply selects the welcome view to render by returning void and relying
	 * on the default request-to-view-translator.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void welcome() {
	}
}
