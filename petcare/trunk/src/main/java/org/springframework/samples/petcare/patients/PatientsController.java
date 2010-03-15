package org.springframework.samples.petcare.patients;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.samples.petcare.util.EntityReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/patients")
public class PatientsController {

	private final JdbcTemplate jdbcTemplate;
	
	@Inject
	public PatientsController(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<EntityReference> getPatients(@RequestParam String name) {
		return jdbcTemplate.query("select p.id, p.name, (c.firstName || ' ' || c.lastName) as client from Patient p, Client c where lower(p.name) like ? and p.clientId = c.id",
				new RowMapper<EntityReference>() {
					public EntityReference mapRow(ResultSet rs, int row)
							throws SQLException {
						Long id = rs.getLong("ID");
						String label = rs.getString("NAME") + " (" + rs.getString("CLIENT") + ")";
						return new EntityReference(id, label);
					}
				}, name.toLowerCase() + "%");
	}
}
