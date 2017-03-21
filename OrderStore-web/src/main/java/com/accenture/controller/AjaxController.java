package com.accenture.controller;

import com.accenture.model.Product;
import com.accenture.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by antonio.meza.flores on 3/17/2017.
 */

@RestController
public class AjaxController {

    @Autowired
    private ProductService productService;

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }

    List<Product> allProducts = null;

    @PostMapping(value = "/order/new/search")
    private List<Product> searchProductByDescription(@RequestBody String desc) {
       // String description = desc.replace("\"","");
        if(allProducts==null){
            try {
                allProducts= productService.getAllProducts();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<Product>  products = this.getAllProducts().stream().filter(product ->  product.getDescription().contains(desc)).collect(Collectors.toList());
        return products;
    }
}
