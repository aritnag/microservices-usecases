package com.restful.microservices.example.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restful.microservices.example.response.Product;
import com.restful.microservices.example.service.ProductService;


@RestController
@RequestMapping("/addproducts")
public class AddProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "/addproduct" }, method = RequestMethod.POST)
	public Object addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}

}
