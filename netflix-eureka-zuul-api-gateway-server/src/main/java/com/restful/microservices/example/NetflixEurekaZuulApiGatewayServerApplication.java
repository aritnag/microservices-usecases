package com.restful.microservices.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class NetflixEurekaZuulApiGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaZuulApiGatewayServerApplication.class, args);
	}
}
