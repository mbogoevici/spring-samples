package org.springframework.samples.petcare.clients;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/clients")
public class ClientsController {

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Client> getClients(@RequestParam String criteria) {
		return Collections.emptyList();
	}
}
