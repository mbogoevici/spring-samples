package org.springframework.samples.petclinic.clients.patients;

public interface PatientRepository {

	Patient getPatient(Long ownerId, String name);

	void savePatient(Patient pet);

}
