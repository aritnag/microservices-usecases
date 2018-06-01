package com.microservices.poc.restfulwebservices.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.poc.restfulwebservices.dao.UserDAO;
import com.microservices.poc.restfulwebservices.dao.UserDAOCustom;
import com.microservices.poc.restfulwebservices.request.User;
import com.microservices.poc.restfulwebservices.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserDAOCustom userDaoCustom;
	
	@Autowired
	private NextSequenceService nextSequenceService;

	@Override
	public List<User> getUsers() {
		List<com.microservices.poc.restfulwebservices.entity.User> users = userDao.findAll();
		List<User> finalReturnList = new ArrayList<User>();
		for (com.microservices.poc.restfulwebservices.entity.User user : users) {
			User reUser = new User();
			BeanUtils.copyProperties(user, reUser);
			finalReturnList.add(reUser);
		}
		return finalReturnList;
	}

	@Override
	public User addUser(User user) {
		com.microservices.poc.restfulwebservices.entity.User entityUser=new com.microservices.poc.restfulwebservices.entity.User();
		BeanUtils.copyProperties(user, entityUser);
		entityUser.setId(nextSequenceService.getNextSequence("customSequences"));
		entityUser=userDao.insert(entityUser);
		user.setId(entityUser.getId());
		return user;
	}

	@Override
	public User getUserById(Integer id) {
		User user = null;
		com.microservices.poc.restfulwebservices.entity.User entityUser = userDaoCustom.fetchUser(id);
		if (null != entityUser) {
			user = new User();
			BeanUtils.copyProperties(entityUser, user);
		}
		return user;
	}

	@Override
	public User deleteUser(String id) {
		User user = null;
		com.microservices.poc.restfulwebservices.entity.User entityUser = userDaoCustom.fetchUser(Integer.parseInt(id));
		if (null != entityUser) {
			user = new User();
			BeanUtils.copyProperties(entityUser, user);
			userDaoCustom.deleteUserById(Integer.parseInt(id));
		}
		return user;
	}
}
