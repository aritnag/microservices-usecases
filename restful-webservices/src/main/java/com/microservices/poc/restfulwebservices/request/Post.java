package com.microservices.poc.restfulwebservices.request;

import io.swagger.annotations.ApiModel;

@ApiModel(description="Post Details")
public class Post {
	private Integer id;
	private String postDetails;
	private User user;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the postDetails
	 */
	public String getPostDetails() {
		return postDetails;
	}
	/**
	 * @param postDetails the postDetails to set
	 */
	public void setPostDetails(String postDetails) {
		this.postDetails = postDetails;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	

}
