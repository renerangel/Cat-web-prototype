package com.accenture.service;

import org.springframework.stereotype.Service;

import com.accenture.model.SaleOrder;
import com.accenture.rest.api.ManagementOrderAPI;
import com.accenture.util.RetrofitClient;

@Service
public class OrderServiceImpl implements OrderService {

	public static final String BASE_URL = "http://localhost:8090/";
	
	@Override
	public boolean createOrder(SaleOrder order) throws Exception {
		return RetrofitClient.getClient(BASE_URL).create(ManagementOrderAPI.class).createOrder(order).execute().body();
	}

}
