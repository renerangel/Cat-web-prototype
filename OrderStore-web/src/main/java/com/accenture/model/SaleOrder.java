package com.accenture.model;

import java.util.List;

public class SaleOrder {
	
	private Integer orderNumber;
	private String customerName;
	private List<OrderProduct> products;
	private Double total;
	
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<OrderProduct> getProducts() {
		return products;
	}
	public void setProducts(List<OrderProduct> products) {
		this.products = products;
	}
	
	
	
}
