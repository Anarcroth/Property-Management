package com.property.manager.controllers;

import java.util.List;
import java.util.Map;

import com.property.manager.models.Property;
import com.property.manager.services.IPropertyService;
import com.property.manager.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by EKAC on 12.10.2017 г..
 */

@Controller
public class PropertyController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);

	private final IPropertyService propertyService;

	private static IUserService userService = null;

	@Autowired
	public PropertyController(IPropertyService propertyService, IUserService userService) {

		this.propertyService = propertyService;
		this.userService = userService;
	}

	@RequestMapping("/prop")
	public String loadPropertiesPage(
			@RequestParam(name = "action", required = false) String action,
			@RequestParam(name = "propertyId", required = false) String propertyId,
			Map<String, Object> model,
			Authentication authentication) {

		LOGGER.info("Loading properties.");

		if (action == null) {
			action = "listAllProperties";
		}
		switch (action) {
			case "viewProperty":
				action = null;
				return viewProperty(Integer.parseInt(propertyId), model, authentication);
			case "listAllProperties":
				action = null;
				return listAllProperties(model, authentication);
			case "deleteProperty":
				action = null;
				return deleteProperty(Integer.parseInt(propertyId), model, authentication);
			default:
				return listAllProperties(model, authentication);
		}
	}

	public String listAllProperties(Map<String, Object> model, Authentication authentication) {

		LOGGER.info("Getting all properties");

		List<Property> list = propertyService.getAllProperties();
		model.put("properties", list);
		model.put("user", userService.getUserByUsername(authentication.getName()));

		return "properties";
	}

	public String viewProperty(int propertyId, Map<String, Object> model, Authentication authentication) {

		LOGGER.info("Getting property by Id");

		Property property = propertyService.getPropertyById(propertyId);
		model.put("property", property);
		model.put("user", userService.getUserByUsername(authentication.getName()));

		return "viewProperty";
	}

	public String deleteProperty(int propertyId, Map<String, Object> model, Authentication authentication) {

		LOGGER.info("Deleting property");

		propertyService.deleteProperty(propertyId);

		List<Property> list = propertyService.getAllProperties();
		model.put("properties", list);
		model.put("user", userService.getUserByUsername(authentication.getName()));

		return "properties";
	}

	@RequestMapping(value = "/prop/addProperty")
	public RegisterResult addProperty(
			@RequestParam(name = "type") String type,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "description") String description,
			@RequestParam(name = "forSale") boolean forSale,
			@RequestParam(name = "forRent") boolean forRent,
			@RequestParam(name = "numberOfRooms") int numberOfRooms,
			@RequestParam(name = "numberOfBedrooms") int numberOfBedrooms,
			@RequestParam(name = "numberOfBathrooms") int numberOfBathrooms,
			@RequestParam(name = "price") double price,
			@RequestParam(name = "rentPerMonth") double rentPerMonth) {

		LOGGER.info("Adding new property");

		Property property = new Property(
				propertyService.getAllProperties().size() + 1, type, address, description, forSale, forRent,
				numberOfRooms, numberOfBedrooms, numberOfBathrooms, price, rentPerMonth, "");

		propertyService.addProperty(property);

		return new RegisterResult(true);
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
