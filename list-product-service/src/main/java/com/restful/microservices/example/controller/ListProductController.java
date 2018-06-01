package com.restful.microservices.example.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restful.microservices.example.service.ProductService;


@RestController
@RequestMapping("/listproducts")
public class ListProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "/getAllProducts" }, method = RequestMethod.GET)
	public Object getAllProducts() {
		return productService.getAllProducts();
	}

}
