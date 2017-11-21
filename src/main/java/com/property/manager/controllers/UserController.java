package com.property.manager.controllers;

import java.util.List;

import com.property.manager.models.Offer;
import com.property.manager.models.Property;
import com.property.manager.models.User;
import com.property.manager.services.IOfferService;
import com.property.manager.services.IPropertyService;
import com.property.manager.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UserController {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static IUserService userService = null;
	private static IPropertyService propertyService = null;
	private static IOfferService offerService = null;


	@Autowired
	public UserController(IUserService userService, IPropertyService propertyService, IOfferService offerService) {

		this.propertyService = propertyService;
		this.userService = userService;
		this.offerService = offerService;
	}

	@GetMapping("/usr/id")
	public String getUserById(
			@RequestParam(name = "userId", required = true) String id,
			@RequestParam(name = "propId", required = true) String propId,
			Model model,
			Authentication authentication) {

		LOGGER.info("getting user by id");

		User user = userService.getUserByUsername(authentication.getName());
		User profile = userService.getUserByID(id);
		Property property = propertyService.getPropertyById(Integer.parseInt(propId));
		List<Offer> offerList = offerService.getAllOffers();

		model.addAttribute("profile", profile);
		model.addAttribute("offers", offerList);
		model.addAttribute("newOffer", new Offer());
		model.addAttribute("user", user);
		model.addAttribute("property", property);

		return "viewProperty";
	}

	@RequestMapping(value = "/usr", method = GET)
	public ResponseEntity<List<User>> getAllUsers() {

		LOGGER.info("getting all users");

		List<User> users = userService.getAllUsers();

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
}

//@Controller
//public class UserViewController {
//
//	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
//
//	private static IUserService userService = null;
//
//	@Autowired
//	public UserViewController(IUserService userService) {
//
//		this.userService = userService;
//	}
//
//	@GetMapping("/usr/id")
//	public String getUserById(
//			@RequestParam(name = "userId", required = true) String id,
//			Model model) {
//
//		LOGGER.info("getting user by id");
//
//		User user = userService.getUserByID(id);
//
//		model.addAttribute("profile", user);
//
//		return "viewProperty";
//	}
//}
