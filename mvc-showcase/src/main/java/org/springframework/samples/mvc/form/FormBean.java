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
        sb.append("properties name='").append(name).append("', ");
        sb.append("age=").append(age).append(", ");
        sb.append("date=").append(date).append(", ");
        sb.append("email='").append(email).append("', ");
        sb.append("phone='").append(phone).append("', ");
        sb.append("currency=").append(currency).append(", ");
        sb.append("percent=").append(percent).append(", ");
        sb.append("inquiry=").append(inquiry).append(", ");
        sb.append("inquiryDetails=").append(inquiryDetails).append(", ");
        sb.append("subscribeNewsletter=").append(subscribeNewsletter).append(", ");
        sb.append("additionalInfo=").append(additionalInfo).append(", ");
        sb.append("password=").append(password).append(", ");
        sb.append("confirmPassword=").append(confirmPassword);
        return sb.toString();
    }
}
