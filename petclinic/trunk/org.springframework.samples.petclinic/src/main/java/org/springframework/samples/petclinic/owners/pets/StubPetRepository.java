package org.springframework.samples.petclinic.owners.pets;

import org.springframework.stereotype.Repository;

@Repository
public class StubPetRepository implements PetRepository {

	public Pet getPet(Long owner, String name) {
		return new Pet();
	}

	public void savePet(Pet pet) {
	}

}
