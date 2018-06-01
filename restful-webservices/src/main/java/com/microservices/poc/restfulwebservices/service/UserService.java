package com.microservices.poc.restfulwebservices.service;

import java.util.List;

import com.microservices.poc.restfulwebservices.request.User;

public interface UserService {
	
	public List<User> getUsers();
	public User addUser(User user);
	public User getUserById(Integer id);
	public User deleteUser(String id);

}
