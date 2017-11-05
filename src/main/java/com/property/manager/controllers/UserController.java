package com.property.manager.controllers;

import java.util.List;

import com.property.manager.models.User;
import com.property.manager.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserController {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static IUserService userService = null;

	@Autowired
	public UserController(IUserService userService) {

		this.userService = userService;
	}

	@RequestMapping(value = "/usr", method = GET)
	public ResponseEntity<List<User>> getAllUsers() {

		LOGGER.info("getting all users");

		List<User> users = userService.getAllUsers();

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
}
