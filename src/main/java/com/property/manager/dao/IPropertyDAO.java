package com.property.manager.dao;

import java.util.List;

import com.property.manager.models.Property;

/**
 * Created by EKAC on 12.10.2017 г..
 */
public interface IPropertyDAO {

	List<Property> getAllProperties();

	Property getPropertyById(int propertyId);

	void addProperty(Property property);

	boolean propertyExists(String type, String address, String description, boolean forSale, boolean forRent, int numberOfRooms, int numberOfBedrooms, int numberOfBathrooms, double price, double rentPerMonth, String pictureUrl);


}
