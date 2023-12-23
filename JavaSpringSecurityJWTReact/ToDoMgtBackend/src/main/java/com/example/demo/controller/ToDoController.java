package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ToDoDto;
import com.example.demo.service.ToDoService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class ToDoController {

	
	ToDoService service;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ToDoDto> addToDo(@RequestBody ToDoDto tododto) {
		
		ToDoDto dto = service.addToDo(tododto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("{id}")
	public ResponseEntity<ToDoDto> getToDo(@PathVariable("id") long id) {
		
		ToDoDto dto = service.getToDo(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping
	public ResponseEntity<List<ToDoDto>> getToDos() {
		
		List<ToDoDto> dto = service.getToDos();
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<ToDoDto> updateToDo(@PathVariable("id") long id,@RequestBody    ToDoDto todoto) {
		
		ToDoDto dto = service.updToDo(id, todoto);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public String deleteToDo(@PathVariable("id") long id) {
		return service.delToDo(id);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PatchMapping("{id}/complete")
	public ResponseEntity<ToDoDto> compToDo(@PathVariable("id") long id){
		ToDoDto dto = service.completeToDo(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PatchMapping("{id}/incomplete")
	public ResponseEntity<ToDoDto> incompToDo(@PathVariable("id") long id){
		ToDoDto dto = service.incompleteToDo(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	//
	
}
