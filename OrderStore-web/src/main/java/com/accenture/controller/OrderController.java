package com.accenture.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.accenture.model.Product;
import com.accenture.service.ProductServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.accenture.model.OrderProduct;
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
		List <OrderProduct> products = new ArrayList<>();
		saleOrder.setProducts(products);
		model.addAttribute("order", saleOrder);

		try{
			/**
			Product product = productService.getProducById(5);
		LOGGER.info("Product added: "+product.getId() + "Description: "+product.getDescription());

		SaleOrder order = new SaleOrder();
		order.setCustomerName("Rene");
		order.setOrderNumber(04151);
		List <OrderProduct> products = new ArrayList<>();

		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setIdProduct(product.getId());
		orderProduct.setQuantity(2);
		orderProduct.setPrice(product.getPrice());
		products.add(orderProduct);

		order.setProducts(products);
		order.setTotal(product.getPrice()*2);

		Boolean orderAdded = orderService.createOrder(order);
		LOGGER.info("Order added is: "+orderAdded);
		*/

		}catch(Exception e){
			LOGGER.info("Error calling restful service "+e.getMessage());
		}
		return ORDER_PAGE;
	}

	@GetMapping(value = "/order/new/refresh")
	public String refresh(@RequestParam("searchValue") String searchValue, Model model) {
		List<Product> productList = null;
		try {
			for(Product pr : productService.getAllProducts()){
				LOGGER.info("PRODUCT: " + pr.getDescription() + "ID: " + pr.getId() + "INVENTORY: " + pr.getInventory()
						+ "PRICE: " + pr.getPrice());
			}
			productList = productService.getAllProducts().stream().filter(product ->  product.getDescription().contains(searchValue)).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("productList", productList);

		return ORDER_PAGE + " :: #productsTable";
	}
	
	@PostMapping
	@RequestMapping(value = "/order/save", params = {"AddProduct"})
	public String addRow(@ModelAttribute("order") SaleOrder order, Model model,  
			final HttpServletRequest req) {
			final Integer productId = Integer.valueOf(req.getParameter("AddProduct"));
			LOGGER.info("Product Id to add the shopping car: "+productId);
			LOGGER.info("Order: "+order.getCustomerName());
			try {
				Product product = productService.getProducById(productId); 
				OrderProduct orderProduct = new OrderProduct(product.getId(), product.getPrice(), product.getDescription());
				if(order.getProducts() != null){
					LOGGER.info("Products order: "+order.getProducts().size());
					order.getProducts().add(orderProduct);
				}else {
					LOGGER.info("Products order is null ");
					List <OrderProduct> products = new ArrayList<>();
					products.add(orderProduct);
					order.setProducts(products);
				}
			}catch(Exception e){
				LOGGER.error("Error when we trying to add product to shopping car" + e.getMessage());
			}
	        return ORDER_PAGE;
	}


	@RequestMapping(value="/order/save", params={"removeRow"})
	public String removeRow(@ModelAttribute("order") SaleOrder order, final BindingResult bindingResult, 
	        final HttpServletRequest req) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	    order.getProducts().remove(rowId.intValue());
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
