package com.accenture.model;



public class OrderProduct {


	private Integer idProduct;
	private Double price;
	private Integer quantity;
	private String description;

	public OrderProduct() {
	}

	public OrderProduct(Integer idProduct, Double price, String description) {
		this.idProduct = idProduct;
		this.price = price;
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
