package com.property.manager.controllers;

import java.util.List;

import com.property.manager.models.User;
import com.property.manager.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserController {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static IUserService userService = null;

	@Autowired
	public UserController(IUserService userService) {

		this.userService = userService;
	}

	@RequestMapping(value = "/users", method = GET)
	public ResponseEntity<List<User>> getAllUsers() {

		LOGGER.info("getting all users");

		List<User> users = userService.getAllUsers();

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = POST)
	public ResponseEntity<String> addUser(@ModelAttribute User user) {

		LOGGER.info("adding user");

		if (userService.addUser(user)) {
			return new ResponseEntity<String>("added", HttpStatus.CREATED);

		} else {
			return new ResponseEntity<String>("failed", HttpStatus.I_AM_A_TEAPOT);
		}
	}
}
