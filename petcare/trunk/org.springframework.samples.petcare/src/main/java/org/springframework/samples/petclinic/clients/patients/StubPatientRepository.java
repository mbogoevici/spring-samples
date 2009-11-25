package org.springframework.samples.petclinic.clients.patients;

import org.springframework.stereotype.Repository;

@Repository
public class StubPatientRepository implements PatientRepository {

	public Patient getPatient(Long owner, String name) {
		return new Patient();
	}

	public void savePatient(Patient patient) {
	}

}
