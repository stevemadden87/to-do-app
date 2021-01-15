package com.codewebs.todo.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewebs.todo.entities.TodoItem;
import com.codewebs.todo.entities.User;
import com.codewebs.todo.service.TodoItemService;
import com.codewebs.todo.service.UserService;


@RestController
@RequestMapping(ToDoController.PATH)
public class ToDoController {

	public static final String PATH = "/todo-api";
	
	private final TodoItemService todoItemService;
	private final UserService userService;
	

	@Autowired
	public ToDoController(TodoItemService todoItemService, UserService userService) {
		this.todoItemService = todoItemService;
		this.userService = userService;
	}

	@GetMapping("/item/{itemId}")
	public TodoItem getItem(@PathVariable Integer itemId) {
		return todoItemService.getItem(itemId);
	}

	// Get todo list for logged in user
	@GetMapping("/list/{userId}")
	public List<TodoItem> getUsersList(@PathVariable UUID userId) {
		User owner = userService.userFindByUserId(userId).orElseThrow();
		return todoItemService.getAllItemsForOwner(owner);
	}

	// New todo item
	@PostMapping(value = "/new")
	public ResponseEntity<TodoItem> newTodoItem(@RequestBody TodoItem item) {
		return ResponseEntity.ok(todoItemService.createNewTodoItem(item));
	}

	// Edit todo item
	@PutMapping("/edit")
	public ResponseEntity<TodoItem> editTodoItem(@RequestBody TodoItem item) {
		TodoItem editItem = todoItemService.getItem(item.getId());
		return ResponseEntity.ok(todoItemService.editTodoItem(editItem));
	}

	// Delete todo item
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteTodoItem(@PathVariable Integer id) {
		TodoItem item = todoItemService.getItem(id);
		return ResponseEntity.ok(todoItemService.deleteTodoItem(item));
	}

	// Change done state
	@PutMapping("/state/{id}")
	public ResponseEntity<TodoItem> changeDoneState(@PathVariable Integer id) {
		TodoItem item = todoItemService.getItem(id);
		return ResponseEntity.ok(todoItemService.changeDoneStateForTodoItem(item));
	}

}
