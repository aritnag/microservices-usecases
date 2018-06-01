package com.microservices.poc.restfulwebservices.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.microservices.poc.restfulwebservices.dao.PostDAOCustom;
import com.microservices.poc.restfulwebservices.entity.Post;

@Component
public class PostDAOCustomImpl implements PostDAOCustom {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Post> findPostsbyUserId(Integer id) {
		Query query = Query.query(Criteria.where("user._id").is(id));
	    return mongoTemplate.find(query, Post.class);
	}

}
