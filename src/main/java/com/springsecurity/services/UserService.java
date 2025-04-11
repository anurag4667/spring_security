package com.springsecurity.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springsecurity.models.User;

@Service
public class UserService {

	List<User> list = new ArrayList<>();
	
	public UserService() {
		list.add(new User("abc","ABC","abc@gmail.com"));

		list.add(new User("xyz","XYZ","xyz@gmail.com"));
	}
	
	
	public List<User> getAllUser(){
		return this.list;
	}
	
	public User getUser(String username) {
		return this.list.stream().filter((user)-> user.getUsername().equals(username)).findAny().orElse(null);
	}
	
	public User addUser(User user) {
		this.list.add(user);
		return user;
	}
	
}
