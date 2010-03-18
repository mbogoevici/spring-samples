package org.springframework.samples.petcare.patients;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.samples.petcare.clients.PatientsController;
import org.springframework.samples.petcare.util.EntityReference;

public class PatientsControllerTest {

	private EmbeddedDatabase database;

	private PatientsController controller;

	@Before
	public void setUp() {
		database = new EmbeddedDatabaseBuilder().
			setType(EmbeddedDatabaseType.H2).
			addScript("schema.sql").
			addScript("data.sql").build();
		controller = new PatientsController(new JdbcTemplate(database));
	}

	@After
	public void tearDown() {
		database.shutdown();
	}

	@Test
	public void autoCompletePatients() {
		List<EntityReference> patients = controller.getPatients("M");
		assertEquals(1, patients.size());
		assertEquals(1L, (long) patients.get(0).getId());
		assertEquals("Macy (Keith Donald)", patients.get(0).getLabel());
	}

}
