package org.springframework.samples.mvc.flash;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class FlashMap {
	
	static final String FLASH_MAP_ATTRIBUTE = FlashMap.class.getName();
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getCurrent(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Map<String, Object> flash = (Map<String, Object>) session.getAttribute(FLASH_MAP_ATTRIBUTE);
		if (flash == null) {
			flash = new HashMap<String, Object>();
			session.setAttribute(FLASH_MAP_ATTRIBUTE, flash);
		}
		return flash;
	}
	
	private FlashMap() {
	}

	public static void put(String key, Object value) {
		getCurrent(getRequest(RequestContextHolder.currentRequestAttributes())).put(key, value);
	}

	public static void setInfoMessage(String info) {
		put(MESSAGE_KEY, new FlashMessage(FlashMessageType.info, info));
	}

	public static void setWarningMessage(String warning) {
		put(MESSAGE_KEY, new FlashMessage(FlashMessageType.warning, warning));
	}

	public static void setErrorMessage(String error) {
		put(MESSAGE_KEY, new FlashMessage(FlashMessageType.error, error));
	}

	public static void setSuccessMessage(String success) {
		put(MESSAGE_KEY, new FlashMessage(FlashMessageType.success, success));
	}

	private static HttpServletRequest getRequest(RequestAttributes requestAttributes) {
		return ((ServletRequestAttributes)requestAttributes).getRequest();
	}

	private static final String MESSAGE_KEY = "message";

	public static final class FlashMessage {
		
		private final FlashMessageType type;
		
		private final String text;

		public FlashMessage(FlashMessageType type, String text) {
			this.type = type;
			this.text = text;
		}

		public FlashMessageType getType() {
			return type;
		}

		public String getText() {
			return text;
		}
		
		public String toString() {
			return type + ": " + text;
		}
	
	}
	
	public static enum FlashMessageType {
		info, success, warning, error
	}
	
}