package org.springframework.samples.petcare.util.flash;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class FlashScope {
	
	static final String FLASH_SCOPE_ATTRIBUTE = FlashScope.class.getName();
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getCurrent(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Map flash = (Map) session.getAttribute(FLASH_SCOPE_ATTRIBUTE);
		if (flash == null) {
			flash = new HashMap();
			session.setAttribute(FLASH_SCOPE_ATTRIBUTE, flash);
		}
		return flash;
	}
	
	private FlashScope() {
	}
	
}
