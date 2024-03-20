package com.user.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entities.User;
import com.user.service.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PutMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = this.userService.saveUser(user);
		return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getSingleUser(@PathVariable Integer id) {
		User user = this.userService.getUser(id);

		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> listUsers = this.userService.getAllUser();
		return new ResponseEntity<List<User>>(listUsers, HttpStatus.OK);
	}

}
