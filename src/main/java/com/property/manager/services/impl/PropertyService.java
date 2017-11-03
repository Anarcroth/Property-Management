package com.property.manager.services.impl;

import java.util.List;

import com.property.manager.dao.IPropertyDAO;
import com.property.manager.models.Property;
import com.property.manager.services.IPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by EKAC on 12.10.2017 г..
 */
@Service
public class PropertyService implements IPropertyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyService.class);

	private final IPropertyDAO propertyDAO;

	@Autowired
	public PropertyService(IPropertyDAO propertyDAO) {

		this.propertyDAO = propertyDAO;
	}

	@Override
	public List<Property> getAllProperties() {
		return propertyDAO.getAllProperties();
	}

	@Override
	public Property getPropertyById(int propertyId) {
		return propertyDAO.getPropertyById(propertyId);
	}

	@Override
	public ResponseEntity<String> addProperty(Property property) {

		try {
			if (propertyDAO.propertyExists(property.getType(), property.getAddress(), property.getDescription(), property.isForSale(), property.isForRent(), property.getNumberOfRooms(), property.getNumberOfBedrooms(), property.getNumberOfBathrooms(), property.getPrice(), property.getRentPerMonth(), property.getPictureUrl())) {

				return new ResponseEntity<>("The property already exists",HttpStatus.CONFLICT);
			}
			else{
				propertyDAO.addProperty(property);
				return new ResponseEntity<>("The property is created",HttpStatus.CREATED);
			}
		}
		catch(Exception e){

			LOGGER.error("Could not add property.", e);
			return new ResponseEntity<>("Something went wrong. Please, check your request",HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void updateProperty(Property property) {

	}

	@Override
	public void deleteProperty(int propertyId) {

	}
}
