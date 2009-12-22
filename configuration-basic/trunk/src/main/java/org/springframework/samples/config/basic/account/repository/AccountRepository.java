package org.springframework.samples.config.basic.account.repository;

import java.util.Set;

import org.springframework.samples.config.basic.account.domain.Account;

public interface AccountRepository {

	Account findById(String acctId);

	void update(Account account);

	void add(Account account);

	Set<Account> findAll();

}
