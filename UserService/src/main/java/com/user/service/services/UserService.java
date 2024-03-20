package com.user.service.services;

import java.util.List;

import com.user.service.entities.User;

public interface UserService {

	// save user
	User saveUser(User user);

	// get all user
	List<User> getAllUser();

	// get user by user id
	User getUser(Integer userId);

	// TODO: delete
	String deleteUser(Integer userId);

	// TODO: update
	User updateUser(Integer userId);

}
