package com.microservices.poc.restfulwebservices.dao;

import java.util.List;

import com.microservices.poc.restfulwebservices.entity.Post;

public interface PostDAOCustom {
	
	public List<Post> findPostsbyUserId(Integer id);

}
