package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ToDoDto;
import com.example.demo.entity.ToDo;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.ToDoRepo;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService{

	private ToDoRepo repo;
	private ModelMapper modelMapper;
	
	
	@Override
	public ToDoDto addToDo(ToDoDto tododto) {

		ToDo todo = modelMapper.map(tododto, ToDo.class);
		ToDo savedToDo = repo.save(todo);
		
		ToDoDto newtododto = modelMapper.map(savedToDo, ToDoDto.class);
		return newtododto;
		
	}


	@Override
	public ToDoDto getToDo(long id) {
		ToDo finedTodo = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo Not Found with given id"));
		ToDoDto newtododto = modelMapper.map(finedTodo, ToDoDto.class);
		return newtododto;
	}


	@Override
	public List<ToDoDto> getToDos() {
		List<ToDo> todos = repo.findAll();
		List<ToDoDto> list = new ArrayList<>();
		for(ToDo todo : todos) {
			ToDoDto newtododto = modelMapper.map(todo, ToDoDto.class);
			list.add(newtododto);
		}
		return list;
		
	}


	@Override
	public ToDoDto updToDo(long id, ToDoDto tododto) {
		ToDo finedTodo = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo Not Found with given id"));
		finedTodo.setTitle(tododto.getTitle());
		finedTodo.setDescription(tododto.getDescription());
		finedTodo.setCompleted(tododto.isCompleted());
		ToDo savedToDo = repo.save(finedTodo);
		ToDoDto newtododto = modelMapper.map(finedTodo, ToDoDto.class);
		return newtododto;
		
	}


	@Override
	public String delToDo(long id) {
		repo.deleteById(id);
		return "ToDo Item deleted Successfully";
	}


	@Override
	public ToDoDto completeToDo(long id) {
		ToDo finedTodo = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo Not Found with given id"));

		finedTodo.setCompleted(Boolean.TRUE);
		ToDo savedToDo = repo.save(finedTodo);
		ToDoDto newtododto = modelMapper.map(finedTodo, ToDoDto.class);
		return newtododto;
	}
	
	
	@Override
	public ToDoDto incompleteToDo(long id) {
		ToDo finedTodo = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo Not Found with given id"));

		finedTodo.setCompleted(Boolean.FALSE);
		ToDo savedToDo = repo.save(finedTodo);
		ToDoDto newtododto = modelMapper.map(finedTodo, ToDoDto.class);
		return newtododto;
	}


	
	
	
	
	
	
}
