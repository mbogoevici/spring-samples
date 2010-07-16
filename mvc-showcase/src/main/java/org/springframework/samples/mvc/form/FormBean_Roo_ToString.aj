package org.springframework.samples.mvc.form;

import java.lang.String;

privileged aspect FormBean_Roo_ToString {
    
    public String FormBean.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("Age: ").append(getAge()).append(", ");
        sb.append("Date: ").append(getDate()).append(", ");
        sb.append("Email: ").append(getEmail()).append(", ");
        sb.append("Phone: ").append(getPhone()).append(", ");
        sb.append("Currency: ").append(getCurrency()).append(", ");
        sb.append("Percent: ").append(getPercent()).append(", ");
        sb.append("Inquiry: ").append(getInquiry()).append(", ");
        sb.append("InquiryDetails: ").append(getInquiryDetails()).append(", ");
        sb.append("SubscribeNewsletter: ").append(isSubscribeNewsletter()).append(", ");
        sb.append("AdditionalInfo: ").append(getAdditionalInfo() == null ? "null" : getAdditionalInfo().size()).append(", ");
        sb.append("Password: ").append(getPassword()).append(", ");
        sb.append("ConfirmPassword: ").append(getConfirmPassword());
        return sb.toString();
    }
    
}
