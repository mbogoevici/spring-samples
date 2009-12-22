package org.springframework.samples.config.basic.account;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.samples.config.basic.account.domain.Account;
import org.springframework.samples.config.basic.account.repository.AccountRepository;
import org.springframework.samples.config.basic.account.service.TransferService;

public class TransferServiceTest {

	@Test
	public void transfer100Dollars() {
		// create the spring container using the AppConfig @Configuration class
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		// retrieve the beans we'll use during testing
		AccountRepository accountRepository = ctx.getBean(AccountRepository.class);
		TransferService transferService = ctx.getBean(TransferService.class);

		// create accounts to test against
		accountRepository.add(new Account("A123", 1000.00));
		accountRepository.add(new Account("C456", 0.00));

		// check account balances before transfer
		assertThat(accountRepository.findById("A123").getBalance(), equalTo(1000.00));
		assertThat(accountRepository.findById("C456").getBalance(), equalTo(0.00));

		// perform transfer
		transferService.transfer(100.00, "A123", "C456");

		// check account balances after transfer
		assertThat(accountRepository.findById("A123").getBalance(), equalTo(900.00));
		assertThat(accountRepository.findById("C456").getBalance(), equalTo(100.00));
	}

}
