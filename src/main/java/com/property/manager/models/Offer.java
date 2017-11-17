package com.property.manager.models;

public class Offer {

	private int userId;
	private int offerId;
	private int propertyId;
	private double offerToBuy;
	private double offerToRent;

	public Offer() {

	}

	public Offer(int userId, int offerId, int propertyId, double offerToBuy, double offerToRent) {

		this.userId = userId;
		this.offerId = offerId;
		this.offerToBuy = offerToBuy;
		this.offerToRent = offerToRent;
		this.propertyId = propertyId;
	}

	public int getUserId() {

		return userId;
	}

	public void setUserId(int userId) {

		this.userId = userId;
	}

	public int getOfferId() {

		return offerId;
	}

	public void setOfferId(int offerId) {

		this.offerId = offerId;
	}

	public int getPropertyId() {

		return propertyId;
	}

	public void setPropertyId(int propertyId) {

		this.propertyId = propertyId;
	}

	public double getOfferToBuy() {

		return offerToBuy;
	}

	public void setOfferToBuy(double offerToBuy) {

		this.offerToBuy = offerToBuy;
	}

	public double getOfferToRent() {

		return offerToRent;
	}

	public void setOfferToRent(double offerToRent) {

		this.offerToRent = offerToRent;
	}
}
