package com.springsecurity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.models.User;
import com.springsecurity.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/")
	public List<User> getAllUser(){
		return this.userservice.findAll();
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username")String username) {
		return (User)this.userservice.findByUsername(username);
	}
	
	
	@GetMapping("/csrf-token")
	public CsrfToken getToken(HttpServletRequest request) {
		
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	@PostMapping("/")
	public User Add(@RequestBody User user) {
		return this.userservice.save(user);
	}
}
