package com.restful.microservices.example.service;

import java.math.BigDecimal;

import com.restful.microservices.example.response.ConversionValue;

public interface CurrencyConversionService {
	
	public ConversionValue findFromAndToByQuantity(String from,String to,BigDecimal quantity);

	public ConversionValue findFromAndToByQuantityFeign(String from, String to, BigDecimal quantity);

}
