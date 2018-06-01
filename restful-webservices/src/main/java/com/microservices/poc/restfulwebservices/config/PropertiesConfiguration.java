package com.microservices.poc.restfulwebservices.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("restful.error.message")
public class PropertiesConfiguration {
	
	private String usernotfound;
	private String idnotsend;
	private String postnotfound;
	
	/**
	 * @param usernotfound the usernotfound to set
	 */
	public void setUsernotfound(String usernotfound) {
		this.usernotfound = usernotfound;
	}
	/**
	 * @param idnotsend the idnotsend to set
	 */
	public void setIdnotsend(String idnotsend) {
		this.idnotsend = idnotsend;
	}
	/**
	 * @return the usernotfound
	 */
	public String getUsernotfound() {
		return usernotfound;
	}
	/**
	 * @return the idnotsend
	 */
	public String getIdnotsend() {
		return idnotsend;
	}
	public String getPostnotfound() {
		return postnotfound;
	}
	public void setPostnotfound(String postnotfound) {
		this.postnotfound = postnotfound;
	}
	

}
