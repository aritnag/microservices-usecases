package com.microservices.poc.restfulwebservices.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.microservices.poc.restfulwebservices.request.Post;
import com.microservices.poc.restfulwebservices.request.User;
import com.microservices.poc.restfulwebservices.service.PostService;

@RestController
@RequestMapping(value = { "/post" })
public class PostController {

	@Autowired
	private PropertiesConfiguration configprops;
	
	@Autowired
	private PostService postService;

	@RequestMapping(value = { "/getposts" }, method = RequestMethod.GET)
	public List<Post> getPosts() {
		return postService.getPosts();
	}

	@RequestMapping(value = { "/addpost" }, method = RequestMethod.POST)
	public ResponseEntity<Object> addPos(@Valid @RequestBody Post post) {
		Post createdPost = postService.addPost(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = { "/getpost/{id}" }, method = RequestMethod.GET)
	public Resource<Post> getPostById(@PathVariable String id) {
		if (!StringUtils.isEmpty(id)) {
			Post post = postService.getPostById(Integer.parseInt(id));
			if (null != post) {
				Resource<Post> resource = new Resource<Post>(post);
				ControllerLinkBuilder linkTo = ControllerLinkBuilder
						.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getPosts());
				resource.add(linkTo.withRel("all-posts"));
				return resource;
			} else {
				throw new UserCustomException(configprops.getPostnotfound());
			}
		} else {
			throw new UserCustomException(configprops.getIdnotsend());
		}
	}
	
	@RequestMapping(value = { "/deletepost/{id}" }, method = RequestMethod.GET)
	public ResponseEntity<Post> deletepost(@PathVariable String id) {		
		if (!StringUtils.isEmpty(id)) {
			Post post = postService.getPostById(Integer.parseInt(id));
			if (null != post) {
				postService.deletePost(id);
				return new ResponseEntity<Post>(post, HttpStatus.FOUND);
			} else {
				throw new UserCustomException(configprops.getPostnotfound());
			}
		}
		return null;
	}

	@RequestMapping(value = { "/getposts/user/{id}" }, method = RequestMethod.GET)
	public ResponseEntity<List<?>> getpostsByUserId(@PathVariable String id) {
		if (!StringUtils.isEmpty(id)) {
			List<Post> posts = postService.getpostsByUserId(Integer.parseInt(id));
			if (null != posts) {
				return new ResponseEntity<List<?>>(posts, HttpStatus.FOUND);
			} else {
				throw new UserCustomException(configprops.getPostnotfound());
			}
		}
		return null;
	}
}
