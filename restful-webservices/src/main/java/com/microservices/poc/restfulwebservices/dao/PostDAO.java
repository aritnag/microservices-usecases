package com.microservices.poc.restfulwebservices.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.poc.restfulwebservices.entity.Post;

public interface PostDAO extends MongoRepository<Post, Integer> {

}
