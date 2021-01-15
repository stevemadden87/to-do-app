package com.codewebs.todo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewebs.todo.entities.TodoItem;
import com.codewebs.todo.entities.User;

@Repository
public interface TodoItemRepository  extends JpaRepository<TodoItem, Integer>{
	
	
	//Optional<TodoItem> findById(Integer id);
	List<TodoItem> findByOwner(User owner);
	

}
