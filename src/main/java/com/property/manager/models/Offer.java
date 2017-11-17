package com.property.manager.models;

public class Offer {

	private int userId;
	private int offerId;
	private int propertyId;
	private String offerType;
	private double offerAmount;

	public Offer() {

	}

	public Offer(int userId, int offerId, int propertyId, String offerType, double offerAmount) {

		this.userId = userId;
		this.offerId = offerId;
		this.offerType = offerType;
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

	public String getOfferType() {

		return offerType;
	}

	public void setOfferType(String offerType) {

		this.offerType = offerType;
	}

	public double getOfferAmount() {

		return offerAmount;
	}

	public void setOfferAmount(double offerAmount) {

		this.offerAmount = offerAmount;
	}
}
