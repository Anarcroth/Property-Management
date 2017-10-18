package com.property.manager.services;

import java.util.List;

import com.property.manager.models.Property;

/**
 * Created by EKAC on 12.10.2017 г..
 */
public interface IPropertyService {

	List<Property> getAllProperties();

	Property getPropertyById(int propertyId);

	boolean addProperty(Property property);

	void updateProperty(Property property);

	void deleteProperty(int propertyId);
}
