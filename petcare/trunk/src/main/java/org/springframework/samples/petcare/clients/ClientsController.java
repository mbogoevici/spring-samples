package org.springframework.samples.petcare.clients;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clients")
public class ClientsController {

	private final ClientRepository repository;

	@Autowired
	public ClientsController(ClientRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ClientSearchForm getSearchForm() {
		return new ClientSearchForm();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Collection<ClientSummary> getSearchResults(
			@RequestParam String lastName) {
		return repository.findClientsByLastName(lastName);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public Client getNewClientForm() {
		return new Client();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Client client) {
		Long ownerId = repository.saveClient(client);
		return "redirect:/owners/" + ownerId;
	}

}
