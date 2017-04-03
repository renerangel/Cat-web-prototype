package com.accenture.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.accenture.model.Product;
import com.accenture.service.OrderService;
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
import sun.rmi.runtime.Log;

@Controller
public class OrderController {

	private static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	private static final String ORDER_PAGE = "order";
	
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;

	@GetMapping
	@RequestMapping( value ={"/order/new/"})
	public String newOrder(ModelMap model) {
		LOGGER.info("You are inside  new order");
		SaleOrder saleOrder = new SaleOrder();
		Date date = new Date();
		int orderNumber = (int) (date.getTime()/1000);
		//
		LOGGER.info("Integer :" + orderNumber);
		LOGGER.info("Long : " + new Date().getTime());
		LOGGER.info("Long Date: " + new Date(new Date().getTime()));
		LOGGER.info("Int Date: " + new Date(((long)orderNumber) * 1000L));

		saleOrder.setOrderNumber(orderNumber);

		List <OrderProduct> products = new ArrayList<>();
		saleOrder.setProducts(products);
		model.addAttribute("order", saleOrder);

		return ORDER_PAGE;
	}

	@GetMapping(value = {"/order/new/refresh","/order/refresh" })
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

		return ORDER_PAGE + " :: #searchTable";
	}
	
	@PostMapping
	@RequestMapping(value = "/order/save", params = {"AddProduct"})
	public String addRow(@Valid @ModelAttribute("order") SaleOrder order, BindingResult result, Model model,
			final HttpServletRequest req) {
			final Integer productId = Integer.valueOf(req.getParameter("AddProduct"));
			LOGGER.info("Product Id to add the shopping car: "+productId);
			LOGGER.info("Order: "+order.getCustomerName());
			LOGGER.info("Order Number" + order.getOrderNumber());
			if(result.hasErrors()) {
				LOGGER.error("------- ERROR ---------------");
				LOGGER.error(result.getFieldError().toString());
				return ORDER_PAGE;
			}
			try {

				Product product = productService.getProducById(productId);
				LOGGER.info("ProductId:" + product.getId());
				OrderProduct orderProduct = new OrderProduct(product.getId(), product.getPrice(), 1,  product.getDescription());
				LOGGER.info("OrderProduct:" + orderProduct.getIdProduct());

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
	public String createNewOrder(@Valid @ModelAttribute("order") SaleOrder order, BindingResult result, Model model) {
		LOGGER.info("Order info: "+order.getCustomerName());

		Boolean orderAdded = null;
		if (result.hasErrors()) {
			LOGGER.error("------- ERROR ---------------");
			LOGGER.error(result.getFieldError().toString());
			return ORDER_PAGE;
		}
		try {
			order.setTotal((order.getTotal()));
			orderAdded = orderService.createOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("orderAdded", orderAdded);
		LOGGER.info("Order added is: "+(Boolean) orderAdded);
		return ORDER_PAGE;
	}

	@RequestMapping( value ="/order/save", params={"clearForm"})
	public String clearForm(@ModelAttribute("order") SaleOrder order, Model model) {
		LOGGER.info("Cleaning the form");
		return "forward:/order/new/";
	}
}
