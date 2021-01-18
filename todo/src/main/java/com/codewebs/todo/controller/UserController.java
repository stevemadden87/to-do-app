package com.codewebs.todo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewebs.todo.entities.TodoItem;
import com.codewebs.todo.entities.User;
import com.codewebs.todo.response.TodoItemResponse;
import com.codewebs.todo.service.SecurityService;
import com.codewebs.todo.service.TodoItemService;
import com.codewebs.todo.service.UserService;
import com.codewebs.todo.validator.UserValidator;

@Controller
public class UserController extends BaseController {

	private final UserService userService;
	private final SecurityService securityService;
	private final UserValidator userValidator;
	private final TodoItemService todoItemService;

	@Autowired
	public UserController(UserService userService, SecurityService securityService, UserValidator userValidator,
			TodoItemService todoItemService) {
		super(userService);
		this.userService = userService;
		this.securityService = securityService;
		this.userValidator = userValidator;
		this.todoItemService = todoItemService;

	}

	@GetMapping("/swagger")
	public String swagger() {
		return "redirect:/swagger-ui/index.html";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userService.save(userForm);
		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
		return "redirect:/welcome";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@GetMapping({ "/", "/welcome" })
	public String welcome(@RequestParam(value = "currentPage", defaultValue = "0") Integer currentPage,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Model model) {
		Pageable paging = PageRequest.of(currentPage, size);
		Page<TodoItem> userItems = todoItemService.getAllItemsForOwnerPaginated(getActiveUserFromSession(), paging);
		model.addAttribute("noItems", userItems.getSize());
		model.addAttribute("totalItems", userItems.getTotalElements());
		model.addAttribute("noOfPages", userItems.getTotalPages());
		model.addAttribute("pageNum", paging.getPageNumber());
		model.addAttribute("size", paging.getPageSize());
		model.addAttribute("todos",
				userItems.stream().map(userItem -> buildTodoItemResponse(userItem)).collect(Collectors.toList()));
		return "welcome";
	}
	public TodoItemResponse buildTodoItemResponse(TodoItem userItem) {
		return new TodoItemResponse(userItem);
	}

}
