package org.springframework.samples.petclinic.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/clients/{id}")
public class ClientController {

	private final ClientRepository repository;

	@Autowired
	public ClientController(ClientRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get(@PathVariable Long id, Model model) {
		model.addAttribute(repository.getClient(id));
		return "client";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public Client getClientForEditing(@PathVariable Long id) {
		return repository.getClient(id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(Client client) {
		repository.saveClient(client);
	}

}