package com.microservices.poc.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserCustomException extends RuntimeException {

	public UserCustomException(String message) {
		super(message);
	}


	
	
	/*

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;
	private String methodName;
	private String request;
	*//**
	 * @param errorCode
	 * @param errorMessage
	 * @param methodName
	 * @param request
	 *//*
	public UserCustomException(String errorCode, String errorMessage, String methodName, String request) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.methodName = methodName;
		this.request = request;
	}
	*//**
	 * @return the errorCode
	 *//*
	public String getErrorCode() {
		return errorCode;
	}
	*//**
	 * @param errorCode the errorCode to set
	 *//*
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	*//**
	 * @return the errorMessage
	 *//*
	public String getErrorMessage() {
		return errorMessage;
	}
	*//**
	 * @param errorMessage the errorMessage to set
	 *//*
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	*//**
	 * @return the methodName
	 *//*
	public String getMethodName() {
		return methodName;
	}
	*//**
	 * @param methodName the methodName to set
	 *//*
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	*//**
	 * @return the request
	 *//*
	public String getRequest() {
		return request;
	}
	*//**
	 * @param request the request to set
	 *//*
	public void setRequest(String request) {
		this.request = request;
	}
	

*/}
