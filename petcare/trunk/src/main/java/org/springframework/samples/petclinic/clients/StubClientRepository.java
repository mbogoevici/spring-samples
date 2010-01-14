package org.springframework.samples.petclinic.clients;

import java.util.Collection;
import java.util.Collections;

import org.springframework.stereotype.Repository;

@Repository
public class StubClientRepository implements ClientRepository {

	public Collection<ClientSummary> findClientsByLastName(String lastName) {
		return Collections.emptySet();
	}

	public Client getClient(Long id) {
		return new Client(id);
	}

	public Long saveClient(Client client) {
		return 1L;
	}

}
