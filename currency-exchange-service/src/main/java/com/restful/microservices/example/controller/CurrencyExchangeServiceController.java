package com.restful.microservices.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restful.microservices.example.response.ExchangeValue;
import com.restful.microservices.example.service.CurrencyExchangeService;

@RestController
@RequestMapping(value = { "/currencyexchange" })
public class CurrencyExchangeServiceController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeService currencyExchangeService;
	
	@RequestMapping(value = { "/retrieveExchangeValue/from/{from}/to/{to}" }, method = RequestMethod.GET)
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue = new ExchangeValue();
		exchangeValue=currencyExchangeService.findByFromAndTo(from, to);
		logger.info("exchange value"+exchangeValue);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
}
