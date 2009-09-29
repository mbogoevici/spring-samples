package org.springsource.stocktrader.orders;

import java.math.BigDecimal;

public class Order {

	// consider using enums for OrderType and OrderStatus ?
	private int orderType;
	
	private int orderStatus;
	
	private String symbol;
	
	private BigDecimal price;
	
	private double quantity;

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
	
}
