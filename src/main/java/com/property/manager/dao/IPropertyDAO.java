package com.property.manager.dao;

import com.property.manager.models.Property;

import java.util.List;

/**
 * Created by EKAC on 12.10.2017 г..
 */
public interface IPropertyDAO {

    List<Property> getAllProperties();
    Property getPropertyById(int propertyId);
    void addProperty(Property property);

}
