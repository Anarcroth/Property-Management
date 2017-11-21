package com.property.manager.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.property.manager.authen.PasswordHash;
import com.property.manager.models.User;
import com.property.manager.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private static IUserService userService = null;

	@Autowired
	public LoginController(IUserService userService) {

		PasswordHash.init();

		this.userService = userService;
	}

	@RequestMapping(path = "/log")
	public String loadLogin() {

		return "login";
	}

	@RequestMapping(value = "/log/sign_up")
	public RegisterResult signUp(
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "fullName", required = false) String fullName,
			@RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "password", required = false) String password) {

		User newUser = null;

		if (userService.getUserByUsername(username) != null && username
				.equals(userService.getUserByUsername(username))) {

			LOGGER.error("Username already exists.");

			return new RegisterResult(false, "Username already exists.");
		}

		try {
			String hash = PasswordHash.get().generateStorngPasswordHash(password);

			if (!hash.equals("")) {

				newUser = new User(username, fullName, hash, address,email, "USER", 0, 0);
			}

		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {

			LOGGER.error("Could not find key or algorithm.", ex);
		}

		userService.addUser(newUser);

		LOGGER.info("New user signed up and saved to DB.");

		return new RegisterResult(true);
	}

	@RequestMapping(value = "/log/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {

			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		LOGGER.info("User logged out.");

		return "redirect:/log";
	}

	class RegisterResult {

		public Boolean success;
		public String error;

		public RegisterResult(Boolean success) {

			this.success = success;
		}

		public RegisterResult(Boolean success, String error) {

			this.success = success;
			this.error = error;
		}
	}
}
