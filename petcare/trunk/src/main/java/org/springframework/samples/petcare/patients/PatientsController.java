package org.springframework.samples.petcare.patients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/patients")
public class PatientsController {

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Patient> getPatients(String name) {
		return new ArrayList<Patient>();
	}
}
