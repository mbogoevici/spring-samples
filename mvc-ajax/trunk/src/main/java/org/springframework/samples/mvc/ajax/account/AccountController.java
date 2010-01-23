package org.springframework.samples.mvc.ajax.account;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/account")
public class AccountController {
	
	private Map<Long, Account> accounts = new ConcurrentHashMap<Long, Account>();
	
	private Validator validator;
	
	@Autowired
	public AccountController(Validator validator) {
		this.validator = validator;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute(new Account());
		return "account/createForm";
	}

	@RequestMapping(value="/availability", method=RequestMethod.GET)
	public @ResponseBody AvailabilityStatus getAvailability(@RequestParam String name) {
		for (Account a : accounts.values()) {
			if (a.getName().equals(name)) {
				return AvailabilityStatus.notAvailable(name);
			}
		}
		return AvailabilityStatus.available();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String create(@RequestBody Account account) {
		accounts.put(account.assignId(), account);
		return "redirect:/account/" + account.getId();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public @ResponseBody Account get(@PathVariable Long id) {
		Account account = accounts.get(id);
		if (account == null) {
			throw new ResourceNotFoundException(id);
		}
		return account;
	}
}
