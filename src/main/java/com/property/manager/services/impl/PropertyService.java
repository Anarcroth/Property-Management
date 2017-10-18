package com.property.manager.services.impl;

import java.util.List;

import com.property.manager.dao.IPropertyDAO;
import com.property.manager.models.Property;
import com.property.manager.services.IPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by EKAC on 12.10.2017 г..
 */
@Service
public class PropertyService implements IPropertyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

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

		return null;
	}

	@Override
	public boolean addProperty(Property property) {

		try {
			propertyDAO.addProperty(property);

			return true;

		} catch (Exception e) {

			LOGGER.error("Could not add property.", e);
		}

		return false;
	}

	@Override
	public void updateProperty(Property property) {

	}

	@Override
	public void deleteProperty(int propertyId) {

	}
}
