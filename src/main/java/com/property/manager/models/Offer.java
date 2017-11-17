package com.property.manager.models;

public class Offer {

	private int userId;
	private int offerId;
	private int propertyId;
	private boolean offerToBuy;
	private boolean offerToRent;
	private double offerAmount;

	public Offer() {

	}

	public Offer(int userId, int offerId, int propertyId, boolean offerToBuy, boolean offerToRent, double offerAmount) {

		this.userId = userId;
		this.offerId = offerId;
		this.offerToBuy = offerToBuy;
		this.offerToRent = offerToRent;
		this.propertyId = propertyId;
		this.offerAmount = offerAmount;
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

	public boolean isOfferToBuy() {

		return offerToBuy;
	}

	public void setOfferToBuy(boolean offerToBuy) {

		this.offerToBuy = offerToBuy;
	}

	public boolean isOfferToRent() {

		return offerToRent;
	}

	public void setOfferToRent(boolean offerToRent) {

		this.offerToRent = offerToRent;
	}

	public double getOfferAmount() {

		return offerAmount;
	}

	public void setOfferAmount(double offerAmount) {

		this.offerAmount = offerAmount;
	}
}
