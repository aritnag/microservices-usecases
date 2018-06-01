package com.microservices.poc.restfulwebservices.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.poc.restfulwebservices.dao.PostDAO;
import com.microservices.poc.restfulwebservices.dao.PostDAOCustom;
import com.microservices.poc.restfulwebservices.dao.UserDAO;
import com.microservices.poc.restfulwebservices.entity.User;
import com.microservices.poc.restfulwebservices.request.Post;
import com.microservices.poc.restfulwebservices.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDAO postDao;

	@Autowired
	private PostDAOCustom postDaoCustom;
	
	@Autowired
	private UserDAO userDao;

	@Autowired
	private NextSequenceService nextSequenceService;


	@Override
	public List<Post> getPosts() {
		List<com.microservices.poc.restfulwebservices.entity.Post> postList=postDao.findAll();
		List<Post> finalReturnList=new ArrayList<Post>();
		for(com.microservices.poc.restfulwebservices.entity.Post pst:postList){
			Post post=new Post();
			BeanUtils.copyProperties(pst, post);
			com.microservices.poc.restfulwebservices.request.User user=new com.microservices.poc.restfulwebservices.request.User();
			BeanUtils.copyProperties(pst.getUser(), user);
			post.setUser(user);
			finalReturnList.add(post);
		}
		return finalReturnList;
	}
	
	@Override
	public Post addPost(Post post) {
		com.microservices.poc.restfulwebservices.entity.Post entityPost=new com.microservices.poc.restfulwebservices.entity.Post();
		BeanUtils.copyProperties(post, entityPost);
		entityPost.setUser(userDao.findFirstByName(post.getUser().getName()));
		entityPost.setId(nextSequenceService.getNextSequencePost("customSequencesPost"));
		entityPost=postDao.insert(entityPost);
		post.setId(entityPost.getId());
		return post;
	}
	
	@Override
	public Post getPostById(Integer id) {
		Post post = null;
		Optional<com.microservices.poc.restfulwebservices.entity.Post> entityPost = postDao.findById(id);
		if (entityPost.isPresent()) {
			post = new Post();
			BeanUtils.copyProperties(entityPost.get(), post);
			com.microservices.poc.restfulwebservices.request.User user=new com.microservices.poc.restfulwebservices.request.User();
			BeanUtils.copyProperties(entityPost.get().getUser(), user);
			post.setUser(user);
		}
		return post;
	}

	@Override
	public Post deletePost(String id) {
		Post post = null;
		Optional<com.microservices.poc.restfulwebservices.entity.Post> entityPost = postDao.findById(Integer.parseInt(id));
		if (entityPost.isPresent()) {
			post = new Post();
			BeanUtils.copyProperties(entityPost.get(), post);
			postDao.deleteById(Integer.parseInt(id));
		}
		return post;
	}
	
	@Override
	public List<Post> getpostsByUserId(Integer id){
		List<com.microservices.poc.restfulwebservices.entity.Post> entityPosts=postDaoCustom.findPostsbyUserId(id);
		List<Post> finalReturnList=new ArrayList<Post>();
		for(com.microservices.poc.restfulwebservices.entity.Post pst:entityPosts){
			Post post=new Post();
			BeanUtils.copyProperties(pst, post);
			finalReturnList.add(post);
		}
		return finalReturnList;
		
	}
	

}
