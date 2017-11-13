package com.property.manager.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.property.manager.models.Property;
import com.property.manager.services.IPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by EKAC on 12.10.2017 г..
 */

@Controller
public class PropertyController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);

	private final IPropertyService propertyService;

	@Autowired
	public PropertyController(IPropertyService propertyService) {

		this.propertyService = propertyService;
	}

	@RequestMapping("/prop")
	public String loadPropertiesPage(
			@RequestParam(name = "action", required = false) String action,
			@RequestParam(name = "propertyId", required = false) String propertyId,
			Map<String, Object> model) {

		LOGGER.info("Loading properties.");

		if (action == null) {
			action = "listAllProperties";
		}
		switch (action) {
			case "viewProperty":
				action = null;
				return viewProperty(Integer.parseInt(propertyId), model);
			case "listAllProperties":
				action = null;
				return listAllProperties(model);
			case "deleteProperty":
				action = null;
				return deleteProperty(Integer.parseInt(propertyId), model);
			default:
				return listAllProperties(model);
		}
	}

	public String listAllProperties(Map<String, Object> model) {

		LOGGER.info("Getting all properties");

		List<Property> list = propertyService.getAllProperties();
		model.put("properties", list);

		return "properties";
	}

	public String viewProperty(int propertyId, Map<String, Object> model) {

		LOGGER.info("Getting property by Id");

		Property property = propertyService.getPropertyById(propertyId);
		model.put("property", property);

		return "viewProperty";
	}

	public String deleteProperty(int propertyId, Map<String, Object> model) {

		LOGGER.info("Deleting property");

		propertyService.deleteProperty(propertyId);

		List<Property> list = propertyService.getAllProperties();
		model.put("properties", list);

		return "properties";
	}

	@RequestMapping(value = "/addProperty", method = RequestMethod.POST)
	public String addProperty(
			@Valid @ModelAttribute("property") Property property, Map<String, Object> model, Model newModel,
			BindingResult bindingResult) {

		LOGGER.info("Adding new property");

		if (bindingResult.hasErrors()) {

		}

		newModel.addAttribute("new property", new Property());

		propertyService.addProperty(property);

		List<Property> list = propertyService.getAllProperties();
		model.put("property", list);

		return "properties";
	}
}
