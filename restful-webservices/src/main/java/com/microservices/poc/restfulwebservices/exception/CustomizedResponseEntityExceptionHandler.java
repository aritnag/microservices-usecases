/**
 * 
 */
package com.microservices.poc.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author aritnag
 *
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex,WebRequest web){
		 ExceptionResponse expResp= new ExceptionResponse(new Date(), ex.getMessage(), web.getDescription(false));
		 return new ResponseEntity<>(expResp, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UserCustomException.class)
	public final ResponseEntity<Object> handleUserNotFoundAllException(UserCustomException ex,WebRequest web){
		 ExceptionResponse expResp= new ExceptionResponse(new Date(), ex.getMessage(), web.getDescription(false));
		 return new ResponseEntity<>(expResp, HttpStatus.NOT_FOUND);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentException(MethodArgumentNotValidException ex,HttpHeaders headers,
			HttpStatus stats,WebRequest web){
		 ExceptionResponse expResp= new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
		 return new ResponseEntity<>(expResp, HttpStatus.BAD_REQUEST);
	}
}
