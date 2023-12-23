package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ToDoAPIException.class)
	public ResponseEntity<ErrorDetails> handleToDoApiException(
			ToDoAPIException exception, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false));
				
				return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleToDoResourceApiException(
			ResourceNotFoundException exception, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false));
				
				return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		
	}

}
