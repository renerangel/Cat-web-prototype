package com.accenture.model;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderProduct {


	private Integer idProduct;
	@NotNull @Min(1)
	private Double price;
	@NotNull  @Min(1)
	private Integer quantity;
	@NotBlank
	private String description;

	public OrderProduct() {
	}

	public OrderProduct(Integer idProduct, Double price, Integer quantity, String description) {
		this.idProduct = idProduct;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descripcion) {
		this.description = descripcion;
	}
	
}
