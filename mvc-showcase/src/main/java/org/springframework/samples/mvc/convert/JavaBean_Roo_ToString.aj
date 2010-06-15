package org.springframework.samples.mvc.convert;

import java.lang.String;

privileged aspect JavaBean_Roo_ToString {
    
    public String JavaBean.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Number: ").append(getNumber()).append(", ");
        sb.append("Date: ").append(getDate()).append(", ");
        sb.append("Numbers: ").append(getNumbers() == null ? "null" : getNumbers().size()).append(", ");
        sb.append("Fruits: ").append(getFruits() == null ? "null" : getFruits().size());
        return sb.toString();
    }
    
}
