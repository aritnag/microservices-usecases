package com.restful.microservices.example.service;

import com.restful.microservices.example.response.ExchangeValue;

public interface CurrencyExchangeService {
	
	public ExchangeValue findByFromAndTo(String from,String to);

}
