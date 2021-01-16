package com.codewebs.todo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewebs.todo.entities.TodoItem;
import com.codewebs.todo.entities.User;
import com.codewebs.todo.request.Pagination;

@Repository
public interface TodoItemRepository  extends JpaRepository<TodoItem, Integer>{
	
	
	//Optional<TodoItem> findById(Integer id);
	Page<TodoItem> findByOwnerOrderByIdAsc(User owner, org.springframework.data.domain.Pageable paging);



}
