package org.springframework.samples.petclinic.owners;

import java.util.Collection;
import java.util.Collections;

import org.springframework.stereotype.Repository;

@Repository
public class StubOwnerRepository implements OwnerRepository {

	public Collection<OwnerSummary> findOwnersByLastName(String lastName) {
		return Collections.emptySet();
	}

	public OwnerForm getOwner(Long id) {
		return new OwnerForm(id);
	}

	public Long saveOwner(OwnerForm owner) {
		return 1L;
	}

}
