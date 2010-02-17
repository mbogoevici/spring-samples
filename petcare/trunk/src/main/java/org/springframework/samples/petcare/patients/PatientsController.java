package org.springframework.samples.petcare.patients;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/patients")
public class PatientsController {

	private SimpleJdbcTemplate template;
	
	@Autowired
	public PatientsController(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);
	}

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Patient> getPatients(@RequestParam String name) {
		return template.query("select p.id, p.name, c.name as client from Patient p, Client c where p.name like '?%' and p.clientId = c.id",
				new RowMapper<Patient>() {
					public Patient mapRow(ResultSet rs, int row)
							throws SQLException {
						Patient patient = new Patient();
						patient.setId(rs.getLong("ID"));
						patient.setName(rs.getString("NAME"));
						patient.setClient(rs.getString("CLIENT"));
						return patient;
					}
				}, name);
	}
}
