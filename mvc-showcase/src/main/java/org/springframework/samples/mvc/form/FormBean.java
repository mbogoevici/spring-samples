package org.springframework.samples.mvc.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.samples.mvc.convert.MaskFormat;

@RooJavaBean
public class FormBean {
	
	@NotEmpty
	private String name;
	
	@Min(21)
	private int age;

	@DateTimeFormat(iso=ISO.DATE)
	@Past
	private Date birthDate;

	@MaskFormat("(###) ###-####")
	private String phone;

	@NumberFormat(pattern="$###,###.00")
	private BigDecimal currency = new BigDecimal(0.00);

	@NumberFormat(style=Style.PERCENT)
	private BigDecimal percent;
	
	private InquiryType inquiry;
	
	private String inquiryDetails;
	
	private boolean subscribeNewsletter;
	
	private Map<String, String> additionalInfo;
		
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("properties name='").append(name).append("', ");
        sb.append("age=").append(age).append(", ");
        sb.append("date=").append(birthDate).append(", ");
        sb.append("phone='").append(phone).append("', ");
        sb.append("currency=").append(currency).append(", ");
        sb.append("percent=").append(percent).append(", ");
        sb.append("inquiry=").append(inquiry).append(", ");
        sb.append("inquiryDetails=").append(inquiryDetails).append(", ");
        sb.append("subscribeNewsletter=").append(subscribeNewsletter).append(", ");
        sb.append("additionalInfo=").append(additionalInfo);
        return sb.toString();
    }
}
