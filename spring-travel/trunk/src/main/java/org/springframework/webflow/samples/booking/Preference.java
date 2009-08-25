package org.springframework.webflow.samples.booking;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Preference implements Serializable {

    private Long id;

    private boolean smoking;

    private int beds;

    private String creditCard;

    private String creditCardName;

    private int creditCardExpiryMonth;

    private int creditCardExpiryYear;

    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public boolean isSmoking() {
	return smoking;
    }

    public void setSmoking(boolean smoking) {
	this.smoking = smoking;
    }

    public int getBeds() {
	return beds;
    }

    public void setBeds(int beds) {
	this.beds = beds;
    }

    public String getCreditCard() {
	return creditCard;
    }

    public void setCreditCard(String creditCard) {
	this.creditCard = creditCard;
    }

    public String getCreditCardName() {
	return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
	this.creditCardName = creditCardName;
    }

    public int getCreditCardExpiryMonth() {
	return creditCardExpiryMonth;
    }

    public void setCreditCardExpiryMonth(int creditCardExpiryMonth) {
	this.creditCardExpiryMonth = creditCardExpiryMonth;
    }

    public int getCreditCardExpiryYear() {
	return creditCardExpiryYear;
    }

    public void setCreditCardExpiryYear(int creditCardExpiryYear) {
	this.creditCardExpiryYear = creditCardExpiryYear;
    }

    @ManyToOne
    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

}
