package org.springframework.samples.mvc.convert;

import org.springframework.roo.addon.tostring.RooToString;

@RooToString
public final class SocialSecurityNumber {

	private final String value;
	
	public SocialSecurityNumber(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public static SocialSecurityNumber valueOf(String value) {
		return new SocialSecurityNumber(value);
	}
}
