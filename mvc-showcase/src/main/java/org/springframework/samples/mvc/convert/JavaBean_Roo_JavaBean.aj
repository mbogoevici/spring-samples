package org.springframework.samples.mvc.convert;

import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.List;
import java.util.Map;

privileged aspect JavaBean_Roo_JavaBean {
    
    public Integer JavaBean.getNumber() {
        return this.number;
    }
    
    public void JavaBean.setNumber(Integer number) {
        this.number = number;
    }
    
    public Date JavaBean.getDate() {
        return this.date;
    }
    
    public void JavaBean.setDate(Date date) {
        this.date = date;
    }
    
    public List<Integer> JavaBean.getNumbers() {
        return this.numbers;
    }
    
    public void JavaBean.setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
    
    public Map<Integer, String> JavaBean.getFruits() {
        return this.fruits;
    }
    
    public void JavaBean.setFruits(Map<Integer, String> fruits) {
        this.fruits = fruits;
    }
    
}
