package com.restful.microservices.example.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.microservices.example.repository.ExchangeValueRepository;
import com.restful.microservices.example.response.ExchangeValue;
import com.restful.microservices.example.service.CurrencyExchangeService;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService  {
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	public ExchangeValue findByFromAndTo(String from,String to){
		ExchangeValue exchangeValue=new ExchangeValue();
		BeanUtils.copyProperties(exchangeValueRepository.findByFromAndTo(from, to), exchangeValue);
		return exchangeValue;
	}

}
