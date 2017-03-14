package com.accenture.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accenture.model.Product;
import com.accenture.model.SaleOrder;
import com.accenture.service.ProductService;

@Controller
public class OrderController {

	private static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	private static final String ORDER_PAGE = "order";
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	@RequestMapping( value ={"/order/new/"})
	public String newOrder(ModelMap model) {
		LOGGER.info("You are inside  new order");
		SaleOrder saleOrder = new SaleOrder();
		model.addAttribute("order", saleOrder);
		try{
		Product product = productService.getProducById(5);
		LOGGER.info("Product added: "+product.getId() + "Description: "+product.getDescription());
		}catch(Exception e){
			LOGGER.info("Error calling restful service "+e.getMessage());
		}
		return ORDER_PAGE;
	}
	
	@PostMapping
	@RequestMapping( value ={"/order/save"})
	public String createNewBook(@ModelAttribute SaleOrder order, ModelMap model) {
		LOGGER.info("Into the create book method");
		LOGGER.info("Book info: "+order.getCustomerName());
		return ORDER_PAGE;
	}
	
	
	
	
}
