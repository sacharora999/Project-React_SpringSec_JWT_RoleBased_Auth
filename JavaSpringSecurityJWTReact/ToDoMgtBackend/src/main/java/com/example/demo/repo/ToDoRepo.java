package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ToDo;

public interface ToDoRepo extends JpaRepository<ToDo, Long>{

}
