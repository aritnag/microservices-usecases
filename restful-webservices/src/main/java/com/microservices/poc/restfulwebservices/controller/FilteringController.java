package com.microservices.poc.restfulwebservices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.microservices.poc.restfulwebservices.exception.UserCustomException;
import com.microservices.poc.restfulwebservices.request.FilterUser;
import com.microservices.poc.restfulwebservices.request.User;
import com.microservices.poc.restfulwebservices.service.UserService;

@RestController
@RequestMapping(value = { "/user-filtering" })
public class FilteringController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = { "/getuser/{id}" }, method = RequestMethod.GET)
	public MappingJacksonValue getUserById(@PathVariable String id) {
		if (!StringUtils.isEmpty(id)) {
			User user = userService.getUserById(Integer.parseInt(id));
			if (null != user) {
				FilterUser filterUser=new FilterUser();
				BeanUtils.copyProperties(user, filterUser);
				MappingJacksonValue mapping = new MappingJacksonValue(filterUser);
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");
			FilterProvider filters = new SimpleFilterProvider().addFilter("user", filter);
			mapping.setFilters(filters);
			return mapping;
			} else {
				throw new UserCustomException("User Not Present");
			}
		} else {
			throw new UserCustomException("Id not send");
		}
	}
	
	@RequestMapping(value = { "/getusers" }, method = RequestMethod.GET)
	public MappingJacksonValue getUsers() {
		List<User> userList= userService.getUsers();
		List<FilterUser> filterUserList= new ArrayList<FilterUser>();
		for(User user:userList){
			FilterUser filterUser=new FilterUser();
			BeanUtils.copyProperties(user, filterUser);
			filterUserList.add(filterUser);
		}
		MappingJacksonValue mapping = new MappingJacksonValue(filterUserList);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");
		FilterProvider filters = new SimpleFilterProvider().addFilter("user", filter);
		mapping.setFilters(filters);
		return mapping;
	}


	
}
