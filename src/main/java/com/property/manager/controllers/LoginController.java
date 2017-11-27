package com.property.manager.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String loadLogin(Model model) {

		model.addAttribute("user", new User());

		return "login";
	}

	@RequestMapping(value = "/log/sign_up")
	public String signUp(
			@Valid @ModelAttribute(value = "user") User user,
			Model model) {

		User newUser = null;

		if (userService.getUserByUsername(user.getUsername()) != null && user.getUsername()
				.equals(userService.getUserByUsername(user.getUsername()).getUsername())) {

			LOGGER.error("Username already exists.");

			return "login";
		}

		try {
			String hash = PasswordHash.get().generateStorngPasswordHash(user.getPassword());

			if (!hash.equals("")) {

				newUser = new User(
						user.getUsername(), user.getFullName(), hash, user.getAddress(), user.getEmail(), "USER", 0, 0);
			}

		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {

			LOGGER.error("Could not find key or algorithm.", ex);
		}

		userService.addUser(newUser);

		model.addAttribute("usr", newUser);

		LOGGER.info("New user signed up and saved to DB.");

		return "redirect:/login";
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
}
