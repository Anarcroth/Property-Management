package com.property.manager.dao.impl;

import java.util.List;

import com.property.manager.dao.IOfferDAO;
import com.property.manager.models.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class OfferDAO implements IOfferDAO{

	private static final Logger LOGGER = LoggerFactory.getLogger(OfferDAO.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public OfferDAO(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Offer> getAllOffers() {

		return null;
	}

	@Override
	public Offer getOfferById(int offerId) {

		return null;
	}

	@Override
	public void addOffer(Offer offer) {

	}

	@Override
	public void updateOffer(int offerId) {

	}

	@Override
	public void deleteOffer(int offerId) {

	}
}
