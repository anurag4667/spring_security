package com.springsecurity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
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
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		
		return this.userservice.save(user);
	}
}
