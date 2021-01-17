package com.codewebs.todo.controller;

import java.nio.channels.FileChannel.MapMode;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.codewebs.todo.entities.TodoItem;
import com.codewebs.todo.entities.User;
import com.codewebs.todo.service.TodoItemService;
import com.codewebs.todo.service.UserService;

@Controller
public class ToDoController extends BaseController {
	private final TodoItemService todoItemService;
	private final UserService userService;

	@Autowired
	public ToDoController(TodoItemService todoItemService, UserService userService) {
		super(userService);
		this.todoItemService = todoItemService;
		this.userService = userService;
	}

	@GetMapping("/item/{itemId}")
	public TodoItem getItem(@PathVariable Integer itemId) {
		return todoItemService.getItem(itemId);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newTodoItem(@RequestParam String description) {
		todoItemService.createNewTodoItem(description, getActiveUserFromSession());
		return "redirect:/";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editTodoItem(@RequestParam String description, @RequestParam Integer itemId) {
		TodoItem editItem = todoItemService.getItem(itemId);
		todoItemService.editTodoItem(editItem, description);
		return "redirect:/";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam Integer id, @RequestParam(value = "currentPage", defaultValue = "0") Integer currentPage,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		TodoItem item = todoItemService.getItem(id);
		todoItemService.deleteTodoItem(item);
		return "redirect:/?currentPage=" + currentPage + "&size="+size;
	}

	// Change done state
	@RequestMapping(value = "/state", method = RequestMethod.GET)
	public String changeDoneState(@RequestParam Integer id, @RequestParam(value = "currentPage", defaultValue = "0") Integer currentPage,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		TodoItem item = todoItemService.getItem(id);
		ResponseEntity.ok(todoItemService.changeDoneStateForTodoItem(item));
		return "redirect:/?currentPage=" + currentPage + "&size="+size;
	}

}
