package com.accenture.rest.api;

import com.accenture.model.Order;
import com.accenture.model.SaleOrder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ManagementOrderAPI {
	
	@POST("/order")
	Call <Boolean> createOrder(@Body SaleOrder order);
	
	@GET("/orderList")
	Call <Order> getOrderList();
	
	

}
