package com.property.manager.dao;

import java.util.List;

import com.property.manager.models.Offer;

public interface IOfferDAO {

	List<Offer> getAllOffers();

	Offer getOfferById(int offerId);

	void addOffer(Offer offer);

	void updateOffer(int offerId);

	void deleteOffer(int offerId);
}
