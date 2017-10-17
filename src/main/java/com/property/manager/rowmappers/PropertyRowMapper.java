package com.property.manager.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.property.manager.models.Property;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by EKAC on 12.10.2017 г..
 */
public class PropertyRowMapper implements RowMapper<Property> {

	@Override
	public Property mapRow(ResultSet rs, int rowNum) throws SQLException {

		Property property = new Property();
		property.setPropertyId(rs.getInt("property_id"));
		property.setType(rs.getString("type"));
		property.setAddress(rs.getString("address"));
		property.setDescription(rs.getString("description"));
		property.setForSale(rs.getBoolean("for_sale"));
		property.setForRent(rs.getBoolean("for_rent"));
		property.setNumberOfRooms(rs.getInt("no_rooms"));
		property.setNumberOfBedrooms(rs.getInt("no_bedrooms"));
		property.setNumberOfBathrooms(rs.getInt("no_bathrooms"));
		property.setPrice(rs.getDouble("price"));
		property.setRentPerMonth(rs.getDouble("rent_per_month"));
		property.setPictureUrl(rs.getString("photo"));
		return property;
	}
}
