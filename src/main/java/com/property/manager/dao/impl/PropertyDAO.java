package com.property.manager.dao.impl;

import java.util.List;

import com.property.manager.dao.IPropertyDAO;
import com.property.manager.models.Property;
import com.property.manager.rowmappers.PropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by EKAC on 12.10.2017 г..
 */

@Transactional
@Repository
public class PropertyDAO implements IPropertyDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PropertyDAO(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Property> getAllProperties() {

		String sql = "SELECT * FROM property";
		//RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
		RowMapper<Property> rowMapper = new PropertyRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Property getPropertyById(int propertyId) {

		return null;
	}

	@Override
	public void addProperty(Property property) {

		String sql = "INSERT INTO property (property_id,type, address,description,for_sale,for_rent,no_rooms,no_bedrooms,no_bathrooms,price,rent_per_month,photo) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, property.getPropertyId(), property.getType(), property.getAddress(),
				property.getDescription(), property.isForSale(), property.isForRent(), property.getNumberOfRooms(),
				property.getNumberOfBedrooms(), property.getNumberOfBathrooms(), property.getPrice(),
				property.getRentPerMonth(), property.getPictureUrl());
	}

	@Override
	public boolean propertyExists(String type, String address, String description, boolean forSale, boolean forRent, int numberOfRooms, int numberOfBedrooms, int numberOfBathrooms, double price, double rentPerMonth, String pictureUrl) {
		String sql = "SELECT count(*) FROM property WHERE type = ? and address=? and description=? and for_sale=? and for_rent=? and no_rooms=? and no_bedrooms=? and no_bathrooms=? and price=? and rent_per_month=? and photo=?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, type, address, description, forSale, forRent, numberOfRooms, numberOfBedrooms, numberOfBathrooms, price, rentPerMonth, pictureUrl);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
}
