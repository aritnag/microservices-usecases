package com.restful.microservices.example.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restful.microservices.example.proxy.CurrencyConversionServiceProxy;
import com.restful.microservices.example.response.ConversionValue;
import com.restful.microservices.example.service.CurrencyConversionService;

@RestController
@RequestMapping(value = { "/currencyconversion" })
public class CurrencyConversionServiceController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private Environment environment;
	

	@Autowired
	private CurrencyConversionService currencyConversionService;
	

	
	@RequestMapping(value = { "/retrieveConvertedValue/from/{from}/to/{to}/quantity/{quantity}" }, method = RequestMethod.GET)
	public ConversionValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity) {
		ConversionValue responseObject=currencyConversionService.findFromAndToByQuantityFeign(from, to, quantity);
		logger.info("responseObject value"+responseObject);
		return responseObject;
	}
}
