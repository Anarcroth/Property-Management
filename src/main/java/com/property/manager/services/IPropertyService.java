package com.property.manager.services;

import java.util.List;

import com.property.manager.models.Property;
import org.springframework.http.ResponseEntity;

/**
 * Created by EKAC on 12.10.2017 г..
 */
public interface IPropertyService {

	List<Property> getAllProperties();

	Property getPropertyById(int propertyId);

	ResponseEntity addProperty(Property property);

	void updateProperty(int propertyId, double offer);

	void deleteProperty(int propertyId);

	List<Property> filterProperties(
			String forSale, String forRent, String numberOfRooms, String price, String numberOfBedrooms,
			String numberOfBathrooms, String type, String address);

	public void updatePropertyDescription(int propertyId, String descr);
}
