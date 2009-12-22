package org.springframework.samples.config.basic.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.config.basic.account.repository.AccountRepository;
import org.springframework.samples.config.basic.account.repository.InMemoryAccountRepository;
import org.springframework.samples.config.basic.account.service.TransferService;
import org.springframework.samples.config.basic.account.service.TransferServiceImpl;

@Configuration
public class AppConfig {

	public @Bean TransferService transferService() {
		return new TransferServiceImpl(accountRepository());
	}

	public @Bean AccountRepository accountRepository() {
		return new InMemoryAccountRepository();
	}

}
