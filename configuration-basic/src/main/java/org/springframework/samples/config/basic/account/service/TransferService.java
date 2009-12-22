package org.springframework.samples.config.basic.account.service;

public interface TransferService {

	void transfer(double amount, String srcAcctId, String destAcctId);

}
