package com.property.manager.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private static IUserService userService = null;

	@Autowired
	public LoginController(IUserService userService) {

		PasswordHash.init();

		this.userService = userService;
	}

	@RequestMapping(value = "/log")
	@ResponseBody
	public ModelAndView loadLogin() {

		return new ModelAndView("login.html");
	}

	@RequestMapping(value = "/log/sign_up")
	public RegisterResult signUp(
			@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "fullName", required = true) String fullName,
			@RequestParam(name = "address", required = true) String address,
			@RequestParam(name = "password", required = true) String password) {

		User newUser = null;

		if (userService.getUserByUsername(username) != null && username.equals(userService.getUserByUsername(username))) {

			LOGGER.error("Username already exists.");

			return new RegisterResult(false, "Username already exists.");
		}

		try {
			String hash = PasswordHash.get().generateStorngPasswordHash(password);

			if (!hash.equals("")) {

				newUser = new User(username, fullName, hash, address, "USER", 0);
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
