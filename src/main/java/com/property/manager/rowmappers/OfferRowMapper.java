package com.property.manager.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.property.manager.models.Offer;
import org.springframework.jdbc.core.RowMapper;

public class OfferRowMapper implements RowMapper<Offer> {

	@Override
	public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

		Offer offer = new Offer();

		offer.setOfferId(rs.getInt("offer_id"));
		offer.setUserId(rs.getInt("user_id"));
		offer.setPropertyId(rs.getInt("property_id"));
		offer.setOfferToBuy(rs.getDouble("buy"));
		offer.setOfferToRent(rs.getDouble("rent"));

		return offer;
	}

}
