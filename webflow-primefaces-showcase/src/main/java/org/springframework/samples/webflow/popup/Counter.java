package org.springframework.samples.webflow.popup;

import java.io.Serializable;

public class Counter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int confirmations;

	int cancellations;
	
	public void incrementConfirmations() {
		confirmations++;
	}
	
	public void incrementCancellations() {
		cancellations++;
	}

	public int getConfirmations() {
		return confirmations;
	}

	public int getCancellations() {
		return cancellations;
	}
	
}
