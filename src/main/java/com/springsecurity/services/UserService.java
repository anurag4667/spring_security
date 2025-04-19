package com.springsecurity.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.models.User;

@Repository
public interface UserService extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
