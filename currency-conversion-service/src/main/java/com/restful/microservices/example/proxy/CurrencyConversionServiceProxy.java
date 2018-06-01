package com.restful.microservices.example.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.restful.microservices.example.response.ConversionValue;


//@FeignClient(name="currency-exchange-service",url="localhost:8000")
//@FeignClient(name="currency-exchange-service")
@FeignClient(name="netflix-eureka-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyConversionServiceProxy {
	@RequestMapping(value = { "/currency-exchange-service/currencyexchange/retrieveExchangeValue/from/{from}/to/{to}" }, method = RequestMethod.GET)
	public ConversionValue retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
