package com.microservices.poc.restfulwebservices.dao.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.microservices.poc.restfulwebservices.dao.UserDAOCustom;
import com.microservices.poc.restfulwebservices.request.User;
import com.mongodb.client.result.UpdateResult;

@Component
public class UserDAOCustomImpl implements UserDAOCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public long updateUser(String name, User user) {
		Query query = new Query(Criteria.where("name").is(name));
		Update update = new Update();
		update.set("name", user.getName());
		update.set("id", user.getId());
		update.set("birthDate", user.getBirthDate());
		UpdateResult result = mongoTemplate.updateFirst(query, update,
				com.microservices.poc.restfulwebservices.entity.User.class);

		if (result != null)
			return result.getModifiedCount();
		return 0;
	}

	@Override
	public void addUser(User user) {
		com.microservices.poc.restfulwebservices.entity.User insertUser = new com.microservices.poc.restfulwebservices.entity.User();
		BeanUtils.copyProperties(user, insertUser);
		mongoTemplate.insert(insertUser);
	}

	@Override
	public long deleteUser(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		long foundUser = mongoTemplate.count(query, com.microservices.poc.restfulwebservices.entity.User.class);
		if (foundUser > 0)
			mongoTemplate.findAndRemove(query, com.microservices.poc.restfulwebservices.entity.User.class);
		return foundUser;
	}

	@Override
	public com.microservices.poc.restfulwebservices.entity.User fetchUser(Integer id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, com.microservices.poc.restfulwebservices.entity.User.class);
	}

	@Override
	public void deleteUserById(Integer id) {
		Query query = new Query(Criteria.where("_id").is(id));
		mongoTemplate.findAndRemove(query, com.microservices.poc.restfulwebservices.entity.User.class);
	}

}
