package com.property.manager.controllers;

import java.util.List;
import java.util.Map;

import com.property.manager.models.Property;
import com.property.manager.services.IPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

	//Accepts form-data through post requests
	//TODO: check for for null values and duplicate entries(cancel automatically incremented id-s)
	@RequestMapping(value = "/prop/add_prop", method = POST)
	public ResponseEntity addProperty(@ModelAttribute Property property) {

		LOGGER.info("properties get controller");

		return propertyService.addProperty(property);
	}

	public String deleteProperty(int propertyId, Map<String, Object> model) {

		propertyService.deleteProperty(propertyId);

		List<Property> list = propertyService.getAllProperties();
		model.put("properties", list);
		LOGGER.info("Deleted property: " + propertyId);

		return "properties";
	}
}
