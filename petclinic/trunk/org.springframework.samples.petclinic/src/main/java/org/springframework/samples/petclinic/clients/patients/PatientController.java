package org.springframework.samples.petclinic.clients.patients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/owners/{ownerId}/patients/{patient}")
public class PatientController {

	private final PatientRepository repository;

	@Autowired
	public PatientController(PatientRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Patient get(Long ownerId, String patient) {
		return repository.getPatient(ownerId, patient);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public Patient getForEditing(Long ownerId, String patient) {
		return repository.getPatient(ownerId, patient);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(Patient patient) {
		repository.savePatient(patient);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(Long ownerId, String patient) {
	}

}