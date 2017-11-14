package com.property.manager.controllers;

import java.util.List;

import com.property.manager.models.Property;
import com.property.manager.services.IPropertyService;
import com.property.manager.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

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
			Model model,
			Authentication authentication) {

		LOGGER.info("Loading properties.");

		model.addAttribute("property", new Property());

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

	public String listAllProperties(Model model, Authentication authentication) {

		LOGGER.info("Getting all properties");

		List<Property> list = propertyService.getAllProperties();
		model.addAttribute("properties", list);
		model.addAttribute("user", userService.getUserByUsername(authentication.getName()));
		model.addAttribute("property", new Property());

		return "properties";
	}



	public String viewProperty(int propertyId, Model model, Authentication authentication) {

		LOGGER.info("Getting property by Id");

		Property property = propertyService.getPropertyById(propertyId);
		model.addAttribute("property", property);
		model.addAttribute("user", userService.getUserByUsername(authentication.getName()));

		return "viewProperty";
	}

	public String deleteProperty(int propertyId, Model model, Authentication authentication) {

		LOGGER.info("Deleting property");

		propertyService.deleteProperty(propertyId);

		List<Property> list = propertyService.getAllProperties();
		model.addAttribute("properties", list);
		model.addAttribute("user", userService.getUserByUsername(authentication.getName()));

		return "properties";
	}

	@RequestMapping(value = "/prop", method = RequestMethod.POST)
	public String addProperty(
			@ModelAttribute Property property,
			Model model,
			Authentication authentication) {

		LOGGER.info("Adding new property");

		propertyService.addProperty(property);

		List<Property> list = propertyService.getAllProperties();
		model.addAttribute("properties", list);
		model.addAttribute("user", userService.getUserByUsername(authentication.getName()));

		return "properties";
	}
}
