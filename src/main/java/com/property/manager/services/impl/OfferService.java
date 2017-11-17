package com.property.manager.services.impl;

import java.util.List;

import com.property.manager.dao.IOfferDAO;
import com.property.manager.models.Offer;
import com.property.manager.services.IOfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OfferService implements IOfferService{

	private static final Logger LOGGER = LoggerFactory.getLogger(OfferService.class);

	private final IOfferDAO offerDAO;

	@Autowired
	OfferService(IOfferDAO offerDAO) {

		this.offerDAO = offerDAO;
	}

	@Override
	public List<Offer> getAllOffers() {

		return offerDAO.getAllOffers();
	}

	@Override
	public Offer getOfferById(int offerId) {

		return offerDAO.getOfferById(offerId);
	}

	@Override
	public ResponseEntity<String> addOffer(Offer offer) {

		try {
			offerDAO.addOffer(offer);
			return new ResponseEntity<String>("adding", HttpStatus.CREATED);

		} catch (Exception e) {

			LOGGER.error("Could not add property.", e);
			return new ResponseEntity<String>("Something went wrong. Please, check your request", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void updateOffer(int offerId, double amount) {

		offerDAO.updateOffer(offerId, amount);
	}

	@Override
	public void deleteOffer(int offerId) {

		offerDAO.deleteOffer(offerId);
	}
}
