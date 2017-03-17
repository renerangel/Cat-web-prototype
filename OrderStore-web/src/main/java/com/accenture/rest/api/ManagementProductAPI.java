package com.accenture.rest.api;


import com.accenture.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface ManagementProductAPI {

	@GET("/product/{productId}")
	Call<Product> getProduct(@Path("productId") Integer productId);

	@GET("/avial/{productId}")
	Call <Integer> getAvailability(@Path("productId") Integer productId);

	@POST("/create/{description}/{inventory}/{price}")
	Call<Product> createProduct(@Path("description") String description, @Path("inventory") Integer inventory,
			@Path("price") Double price);

	@GET("/products")
	Call <List<Product>> getAllProducts();
}
