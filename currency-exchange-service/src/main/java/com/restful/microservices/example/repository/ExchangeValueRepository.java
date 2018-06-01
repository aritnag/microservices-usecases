package com.restful.microservices.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.microservices.example.entity.ExchangeValue;

@Repository
public interface ExchangeValueRepository
		extends JpaRepository<com.restful.microservices.example.entity.ExchangeValue,Long> {
	
	ExchangeValue findByFromAndTo(String from,String to);

}
