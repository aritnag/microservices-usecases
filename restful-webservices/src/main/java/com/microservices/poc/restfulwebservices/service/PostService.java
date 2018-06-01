package com.microservices.poc.restfulwebservices.service;

import java.util.List;

import com.microservices.poc.restfulwebservices.request.Post;
import com.microservices.poc.restfulwebservices.request.User;

public interface PostService {

	public List<Post> getPosts();

	public Post addPost(Post post);

	public Post getPostById(Integer id);

	public Post deletePost(String id);

	public List<Post> getpostsByUserId(Integer id);

}
