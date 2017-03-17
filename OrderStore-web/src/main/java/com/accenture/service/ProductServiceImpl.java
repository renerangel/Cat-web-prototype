package com.accenture.service;

import org.springframework.stereotype.Service;

import com.accenture.model.Product;
import com.accenture.rest.api.ManagementProductAPI;
import com.accenture.util.RetrofitClient;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

	@Override
	public Product getProducById(Integer id) throws Exception{
		return RetrofitClient.getClient(BASE_URL).create(ManagementProductAPI.class).getProduct(id).execute().body();
	}

	@Override
	public Integer getProductvailability(Integer id) throws Exception{
		return RetrofitClient.getClient(BASE_URL).create(ManagementProductAPI.class).getAvailability(id).execute().body();
	}

	@Override
	public Product addProduct(String description, Integer inventory, Double price) throws Exception {
		return RetrofitClient.getClient(BASE_URL).create(ManagementProductAPI.class).createProduct(description, inventory, price).execute().body();
	}

	@Override
	public List<Product> getAllProducts() throws Exception{
		return RetrofitClient.getClient(BASE_URL).create(ManagementProductAPI.class).getAllProducts().execute().body();
	}
	
	
}
