package com.accenture.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	private static final String INDEX_PAGE = "index";
	
	@GetMapping
	@RequestMapping( value ={"/"})
	public String login() {
		LOGGER.info("You are inside login");
		return INDEX_PAGE;
	}
	
	

}
