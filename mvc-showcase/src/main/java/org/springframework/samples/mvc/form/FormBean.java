package org.springframework.samples.mvc.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.samples.mvc.convert.MaskFormat;

@RooJavaBean
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
	
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("properties name='").append(getName()).append("', ");
        sb.append("age=").append(getAge()).append(", ");
        sb.append("date=").append(getDate()).append(", ");
        sb.append("email='").append(getEmail()).append("', ");
        sb.append("phone='").append(getPhone()).append("', ");
        sb.append("currency=").append(getCurrency()).append(", ");
        sb.append("percent=").append(getPercent()).append(", ");
        sb.append("inquiry=").append(getInquiry()).append(", ");
        sb.append("inquiryDetails=").append(getInquiryDetails()).append(", ");
        sb.append("subscribeNewsletter=").append(isSubscribeNewsletter()).append(", ");
        sb.append("additionalInfo=").append(getAdditionalInfo()).append(", ");
        sb.append("password=").append(getPassword()).append(", ");
        sb.append("confirmPassword=").append(getConfirmPassword());
        return sb.toString();
    }
    
	public static enum InquiryType {
		comment, feedback, suggestion;
	}
}
