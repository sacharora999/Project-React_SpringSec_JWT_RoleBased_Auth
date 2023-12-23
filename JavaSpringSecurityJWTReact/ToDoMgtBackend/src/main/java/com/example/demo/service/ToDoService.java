package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ToDoDto;

public interface ToDoService {

	ToDoDto addToDo(ToDoDto tododto);
	ToDoDto getToDo(long id);
	List<ToDoDto> getToDos();
	ToDoDto updToDo(long id, ToDoDto tododto);
	String delToDo(long id);
	ToDoDto completeToDo(long id);
	ToDoDto incompleteToDo(long id);
	
}
