package com.property.manager.dao.impl;

import java.util.List;

import com.property.manager.dao.IOfferDAO;
import com.property.manager.models.Offer;
import com.property.manager.rowmappers.OfferRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class OfferDAO implements IOfferDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(OfferDAO.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public OfferDAO(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Offer> getAllOffers() {

		String sql = "SELECT * FROM offer";
		RowMapper<Offer> rowMapper = new OfferRowMapper();

		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Offer getOfferById(int offerId) {

		String sql = "SELECT * FROM offfer WHERE offer_id=" + offerId;
		RowMapper<Offer> rowMapper = new OfferRowMapper();

		return (jdbcTemplate.query(sql, rowMapper)).get(0);
	}

	@Override
	public void addOffer(Offer offer) {

		String sql = "INSERT INTO offer (offer_id,user_id,property_id,buy,amount) values (?,?,?,?,?)";
		jdbcTemplate.update(sql, offer.getOfferId(), offer.getUserId(), offer.getPropertyId(), offer.getOfferType(),
				offer.getOfferAmount());

		LOGGER.info("Added new offer");

	}

	@Override
	public void updateOffer(int offerId, double amount) {

		String sql = "UPDATE offer SET amount=? WHERE offer_id=?";
		jdbcTemplate.update(sql, amount, offerId);

		LOGGER.info("Updated amount for offer " + offerId);
	}

	@Override
	public void deleteOffer(int offerId) {

		String sql = "DELETE FROM offer WHERE offer_id=?";
		jdbcTemplate.update(sql, offerId);

		LOGGER.info("Deleted offer from DB");
	}
}
