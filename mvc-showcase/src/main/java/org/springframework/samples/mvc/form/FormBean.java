package org.springframework.samples.mvc.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.samples.mvc.convert.MaskFormat;

@RooJavaBean
@RooToString
public class FormBean {
	
	private String name;
	
	private int age;

	@DateTimeFormat(iso=ISO.DATE)
	private Date date;

	private String email;
	
	@MaskFormat("(###) ###-####")
	private String phone;

	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal currency;

	@NumberFormat(style=Style.PERCENT)
	private BigDecimal percent;
	
	private InquiryType inquiry;
	
	private String inquiryDetails;
	
	private boolean subscribeNewsletter;
	
	private Map<String, String> additionalInfo;
	
	private String password;
	
	private String confirmPassword;
	
	public static enum InquiryType {
		comment, feedback, suggestion;
	}
}
