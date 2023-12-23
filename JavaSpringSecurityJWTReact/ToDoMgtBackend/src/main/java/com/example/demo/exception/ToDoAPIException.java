package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToDoAPIException extends RuntimeException{
	
	private HttpStatus status;
	private String message;

}
