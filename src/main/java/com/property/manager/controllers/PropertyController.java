package com.property.manager.controllers;

import java.util.List;

import com.property.manager.models.Property;
import com.property.manager.services.IPropertyService;
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

/**
 * Created by EKAC on 12.10.2017 г..
 */

@RestController
public class PropertyController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);

	private final IPropertyService propertyService;

	@Autowired
	public PropertyController(IPropertyService propertyService) {

		this.propertyService = propertyService;
	}

	@RequestMapping(value = "/", method = GET)
	public ResponseEntity<List<Property>> getAllProperties() {

		LOGGER.info("properties get controller");
		List<Property> list = propertyService.getAllProperties();

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	//Accepts form-data through post requests
	//TO DO: check for for null values and duplicate entries(cancel automatically incremented id-s)
	@RequestMapping(value = "/", method = POST)
	public ResponseEntity<String> addProperty(@ModelAttribute Property property) {

		LOGGER.info("properties get controller");
		return propertyService.addProperty(property);

	}
}
