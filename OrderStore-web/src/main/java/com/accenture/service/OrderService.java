package com.accenture.service;

import com.accenture.model.SaleOrder;

public interface OrderService {

	public boolean createOrder(SaleOrder order) throws Exception;
	
}
