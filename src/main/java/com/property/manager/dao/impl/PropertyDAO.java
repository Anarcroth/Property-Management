package com.property.manager.dao.impl;

import java.util.List;

import com.property.manager.dao.IPropertyDAO;
import com.property.manager.models.Property;
import com.property.manager.rowmappers.PropertyRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyDAO.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PropertyDAO(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Property> getAllProperties() {

		String sql = "SELECT * FROM property";
		RowMapper<Property> rowMapper = new PropertyRowMapper();

		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Property getPropertyById(int propertyId) {

		String sql = "SELECT * FROM property WHERE property_id=" + propertyId;
		RowMapper<Property> rowMapper = new PropertyRowMapper();

		return (jdbcTemplate.query(sql, rowMapper)).get(0);
	}

	@Override
	public void addProperty(Property property) {

		String sql = "INSERT INTO property (property_id,type,address,description,for_sale,for_rent,no_rooms,no_bedrooms,no_bathrooms,price,rent_per_month,photo,offer) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, property.getPropertyId(), property.getType(), property.getAddress(),
				property.getDescription(), property.isForSale(), property.isForRent(), property.getNumberOfRooms(),
				property.getNumberOfBedrooms(), property.getNumberOfBathrooms(), property.getPrice(),
				property.getRentPerMonth(), property.getPictureUrl(), property.getOffer());

		LOGGER.info("Added new property.");
	}

	@Override
	public boolean propertyExists(
			String type, String address, String description, boolean forSale, boolean forRent, int numberOfRooms,
			int numberOfBedrooms, int numberOfBathrooms, double price, double rentPerMonth, String pictureUrl,
			double offer) {

		String sql = "SELECT count(*) FROM property WHERE type = ? and address=? and description=? and for_sale=? and for_rent=? and no_rooms=? and no_bedrooms=? and no_bathrooms=? and price=? and rent_per_month=? and photo=? and offer=?";
		int count = jdbcTemplate
				.queryForObject(sql, Integer.class, type, address, description, forSale, forRent, numberOfRooms,
						numberOfBedrooms, numberOfBathrooms, price, rentPerMonth, pictureUrl, offer);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void deleteProperty(int propertyId) {

		String sql = "DELETE FROM property WHERE property_id=?";
		jdbcTemplate.update(sql, propertyId);

		LOGGER.info("Deleted property from DB");
	}

	public List<Property> filterProperties(
			String forSale, String forRent, String no_rooms, String price, String no_bedrooms, String no_bathrooms,
			String type, String address) {

		String sql = "SELECT * FROM property WHERE property_id > -1";
		if (forSale != null) {
			sql = sql + " AND for_sale=" + forSale;
		}
		if (forRent != null) {
			sql = sql + " AND for_rent=" + forRent;
		}
		if (no_rooms != null) {
			sql = sql + " AND no_rooms=" + no_rooms;
		}
		if (price != null) {
			if (price.equals("<30,000")) {
				sql = sql + " AND Price<30000";
			}
			if (price.equals("30,000 - 70,000")) {
				sql = sql + " AND Price BETWEEN 30000 AND 70000";
			}
			if (price.equals(">70,000")) {
				sql = sql + " AND Price>70000";
			}
		}
		if (no_bedrooms != null) {
			sql = sql + " AND no_bedrooms=" + no_bedrooms;
		}
		if (no_bathrooms != null) {
			sql = sql + " AND no_bathrooms=" + no_bathrooms;
		}
		if (type != null) {
			if (type.equals("house")) {
				sql = sql + " AND type = 'house'";
			}
			if (type.equals("apartment")) {
				sql = sql + " AND type = 'apartment'";
			}
		}
		if (address != null) {
			sql = sql + " AND address='" + address + "'";
		}

		RowMapper<Property> rowMapper = new PropertyRowMapper();

		return this.jdbcTemplate.query(sql, rowMapper);

	}

	@Override
	public void updateProperty(int propertyId, double offer) {

		String sql = "UPDATE property SET offer=? WHERE property_id=?";
		jdbcTemplate.update(sql, offer, propertyId);

		LOGGER.info("Updated offer for property " + propertyId);
	}

	@Override
	public void updatePropertyDescription(int propertyId, String descr) {

		String sql = "UPDATE property SET description=? WHERE property_id=?";
		jdbcTemplate.update(sql, descr, propertyId);

		LOGGER.info("Updated description of property " + propertyId);
	}
}
