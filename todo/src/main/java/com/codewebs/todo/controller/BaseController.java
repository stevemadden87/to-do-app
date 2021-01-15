package com.codewebs.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.codewebs.todo.entities.User;
import com.codewebs.todo.service.UserService;

@Controller
public class BaseController {

	
	private final UserService userService;

	@Autowired
	public BaseController(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute("user")
	protected User getActiveUserFromSession() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null
				&& authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
			org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) authentication
					.getPrincipal();
			User user = userService.findByUsername((currentUser.getUsername()));
			return user;

		}
		return null;
	}
}