package com.property.manager.services;

import java.util.List;

import com.property.manager.models.Offer;
import org.springframework.http.ResponseEntity;

public interface IOfferService {

	List<Offer> getAllOffers();

	Offer getOfferById(int offerId);

	ResponseEntity addOffer(Offer offer);

	void updateOffer(int offerId, double amount);

	void deleteOffer(int offerId);
}
