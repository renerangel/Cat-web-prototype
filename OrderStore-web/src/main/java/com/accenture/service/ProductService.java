package com.accenture.service;

import com.accenture.model.Product;
public interface ProductService {

	public static final String BASE_URL = "http://localhost:8090/";
	
	public Product getProducById(Integer id) throws Exception;
	
	public Integer getProductvailability(Integer id) throws Exception;
	
	public Product addProduct(String description, Integer inventory, Double price) throws Exception;
	
	
}
