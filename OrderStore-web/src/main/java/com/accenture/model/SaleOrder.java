package com.accenture.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SaleOrder {
	
	private Integer orderNumber;
	@Size(min = 3, max = 15)
	private String customerName;
	private List<OrderProduct> products;
	@Min(0)
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
