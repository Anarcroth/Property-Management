package com.property.manager.controllers;

import java.util.List;
import javax.validation.Valid;

import com.property.manager.models.Offer;
import com.property.manager.models.Property;
import com.property.manager.services.IOfferService;
import com.property.manager.services.IPropertyService;
import com.property.manager.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PropertyController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);

	private static IPropertyService propertyService = null;

	private static IUserService userService = null;

	private static IOfferService offerService = null;

	@Autowired
	public PropertyController(IPropertyService propertyService, IUserService userService, IOfferService offerService) {

		this.propertyService = propertyService;
		this.userService = userService;
		this.offerService = offerService;
	}

	@RequestMapping("/prop")
	public String loadPropertiesPage(
			@RequestParam(name = "action", required = false) String action,
			@RequestParam(name = "propertyId", required = false) String propertyId,
			@RequestParam(name = "numberOfRooms", required = false) String numberOfRooms,
			@RequestParam(name = "forSale", required = false) String forSale,
			@RequestParam(name = "forRent", required = false) String forRent,
			@RequestParam(name = "price", required = false) String price,
			@RequestParam(name = "numberOfBedrooms", required = false) String numberOfBedrooms,
			@RequestParam(name = "numberOfBathrooms", required = false) String numberOfBathrooms,
			@RequestParam(name = "type", required = false) String type,
			@RequestParam(name = "address", required = false) String address,
			Model model,
			Authentication authentication) {

		LOGGER.info("Loading properties.");
		LOGGER.info(action);

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
			case "filterProperties":
				action = null;
				return filterProperties(forSale, forRent, numberOfRooms, price, numberOfBedrooms, numberOfBathrooms,
						type, address, model, authentication);
			default:
				return listAllProperties(model, authentication);
		}
	}

	public String listAllProperties(Model model, Authentication authentication) {

		LOGGER.info("Getting all properties");

		List<Property> list = propertyService.getAllProperties();
		
		model.addAttribute("properties", list);
		model.addAttribute("user", userService.getUserByUsername(authentication.getName()));
		model.addAttribute("prop", new Property());

		return "properties";
	}

	public String filterProperties(
			String forSale, String forRent, String numberOfRooms, String price, String numberOfBedrooms,
			String numberOfBathrooms, String type, String address, Model model, Authentication authentication) {

		LOGGER.info("Filtering");

		if (forSale != null && forSale.equals("on")) {
			forSale = "true";
		}

		if (forRent != null && forRent.equals("on")) {
			forRent = "true";
		}
		if (numberOfRooms.isEmpty()) {
			numberOfRooms = null;
		}
		if (numberOfBedrooms.isEmpty()) {
			numberOfBedrooms = null;
		}
		if (numberOfBathrooms.isEmpty()) {
			numberOfBathrooms = null;
		}
		if (type.isEmpty()) {
			type = null;
		}
		if (address.isEmpty()) {
			address = null;
		}
		LOGGER.info(forRent);

		List<Property> list = propertyService
				.filterProperties(forSale, forRent, numberOfRooms, price, numberOfBedrooms, numberOfBathrooms, type,
						address);
		model.addAttribute("properties", list);
		model.addAttribute("prop", new Property());
		model.addAttribute("user", userService.getUserByUsername(authentication.getName()));

		return "properties";
	}

	public String viewProperty(int propertyId, Model model, Authentication authentication) {

		LOGGER.info("Getting property by Id");

		Property property = propertyService.getPropertyById(propertyId);
		model.addAttribute("property", property);
		model.addAttribute("user", userService.getUserByUsername(authentication.getName()));

		List<Offer> offerList = offerService.getAllOffers();
		for (Offer o : offerList) {
			LOGGER.info("am: " + o.getOfferAmount());
		}
		model.addAttribute("offers", offerList);
		//		model.addAttribute("prop", new Property());

		return "viewProperty";
	}

	public String deleteProperty(int propertyId, Model model, Authentication authentication) {

		LOGGER.info("Deleting property");

		propertyService.deleteProperty(propertyId);

		List<Property> list = propertyService.getAllProperties();
		model.addAttribute("properties", list);
		model.addAttribute("user", userService.getUserByUsername(authentication.getName()));
		model.addAttribute("prop", new Property());

		return "properties";
	}

	@RequestMapping(value = "/prop", method = RequestMethod.POST)
	public String addProperty(
			@Valid @ModelAttribute(value = "prop") Property property,
			Model model,
			Authentication authentication) {

		LOGGER.info("Adding new property");

		propertyService.addProperty(property);

		List<Property> list = propertyService.getAllProperties();
		model.addAttribute("properties", list);
		model.addAttribute("user", userService.getUserByUsername(authentication.getName()));
		model.addAttribute("prop", new Property());

		return "properties";
	}

	@RequestMapping(value = "/makeOffer")
	public String makeOffer(
			@Valid @ModelAttribute(value = "property") Property property,
			Model model,
			Authentication authentication) {

		LOGGER.info("Making an offer");

		propertyService.updateProperty(property.getPropertyId(), property.getOffer());

		List<Property> list = propertyService.getAllProperties();
		model.addAttribute("properties", list);
		model.addAttribute("user", userService.getUserByUsername(authentication.getName()));
		model.addAttribute("prop", new Property());

		return "properties";
	}
}
