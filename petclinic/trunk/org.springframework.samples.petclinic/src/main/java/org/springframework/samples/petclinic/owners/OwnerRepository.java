package org.springframework.samples.petclinic.owners;

import java.util.Collection;

public interface OwnerRepository {

	Collection<OwnerSummary> findOwnersByLastName(String lastName);

	OwnerForm getOwner(Long id);

	Long saveOwner(OwnerForm owner);

}