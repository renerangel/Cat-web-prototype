package com.accenture.controller;

import com.accenture.model.Product;
import com.accenture.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Created by antonio.meza.flores on 4/24/2017.
 */

@Controller
public class ProductController {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private static final String PRODUCT_PAGE = "product";

    @Autowired
    ProductService productService;

    @GetMapping
    @RequestMapping( value ={"/product/new"})
    public String newProduct(ModelMap model) {
        LOGGER.info("You are inside  new product");
        Product product = new Product();
        model.addAttribute("product", product);

        return PRODUCT_PAGE;
    }

    @PostMapping
    @RequestMapping( value ={"/product/save"})
    public String createNewProduct(@ModelAttribute("product") Product product, Model model) {
        try {
            Product product1 = productService.addProduct(product.getDescription(),product.getInventory(),product.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/product/new";
    }
}
