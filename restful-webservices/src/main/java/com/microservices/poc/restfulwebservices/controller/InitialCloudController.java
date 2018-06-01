package com.microservices.poc.restfulwebservices.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.poc.restfulwebservices.response.HelloWorldBean;

@RestController
@RequestMapping(value = { "/web" })
public class InitialCloudController {

	@RequestMapping(value = { "/hello" }, method = RequestMethod.GET)
	public String helloWorld(){
		return "hello world";
	}
	@RequestMapping(value = { "/hello-bean" }, method = RequestMethod.GET)
	public HelloWorldBean helloWorldBean(){
		return new HelloWorldBean("hello world");
	}
	
	@RequestMapping(value = { "/hello-bean/{name}" }, method = RequestMethod.GET)
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name){
		return new HelloWorldBean(String.format("Hello World ,"+name));
	}
}
