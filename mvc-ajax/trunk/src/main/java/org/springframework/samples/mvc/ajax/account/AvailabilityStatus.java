package org.springframework.samples.mvc.ajax.account;

public final class AvailabilityStatus {

	public boolean isAvailable() {
		return available;
	}
	
	public String[] getSuggestions() {
		return suggestions;
	}
	
	public static AvailabilityStatus available() {
		return AVAILABLE_INSTANCE;
	}

	public static AvailabilityStatus notAvailable(String name) {
		String[] suggestions = new String[3];
		for (int i = 0; i < suggestions.length; i++) {
			suggestions[i] = name + (i + 1);
		}
		return new AvailabilityStatus(false, suggestions);
	}
	
	// internal
	
	private static final AvailabilityStatus AVAILABLE_INSTANCE = new AvailabilityStatus(true, new String[0]);
	
	private boolean available;
	
	private String[] suggestions;
	
	private AvailabilityStatus(boolean available, String[] suggestions) {
		this.available = available;
		this.suggestions = suggestions;
	}
	
}