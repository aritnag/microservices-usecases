package com.microservices.poc.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservices.poc.restfulwebservices.config.PropertiesConfiguration;
import com.microservices.poc.restfulwebservices.exception.UserCustomException;
import com.microservices.poc.restfulwebservices.request.User;
import com.microservices.poc.restfulwebservices.service.UserService;

@RestController
@RequestMapping(value = { "/user" })
public class UserController {

	@Autowired
	private PropertiesConfiguration configprops;
	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = { "/getusers" }, method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@RequestMapping(value = { "/adduser" }, method = RequestMethod.POST)
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User createdUser = userService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = { "/getuser/{id}" }, method = RequestMethod.GET)
	public Resource<User> getUserById(@PathVariable String id) {
		if (!StringUtils.isEmpty(id)) {
			User user = userService.getUserById(Integer.parseInt(id));
			if (null != user) {

				Resource<User> resource = new Resource<User>(user);
				ControllerLinkBuilder linkTo = ControllerLinkBuilder
						.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUsers());
				resource.add(linkTo.withRel("all-users"));
				return resource;
			} else {
				throw new UserCustomException(configprops.getUsernotfound());
			}
		} else {
			throw new UserCustomException(configprops.getIdnotsend());
		}
	}

	@RequestMapping(value = { "/deleteuser/{id}" }, method = RequestMethod.GET)
	public ResponseEntity<User> deleteuser(@PathVariable String id) {
		if (!StringUtils.isEmpty(id)) {
			User user = userService.getUserById(Integer.parseInt(id));
			if (null != user) {
				userService.deleteUser(id);
				return new ResponseEntity<User>(user, HttpStatus.FOUND);
			} else {
				throw new UserCustomException(configprops.getUsernotfound());
			}
		}
		return null;
	}
}
