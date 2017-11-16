package com.property.manager.models;

/**
 * Created by EKAC on 12.10.2017 г..
 */
public class Property {

	private int propertyId;
	private String type;
	private String address;
	private String description;
	private boolean forSale;
	private boolean forRent;
	private int numberOfRooms;
	private int numberOfBedrooms;
	private int numberOfBathrooms;
	private double price;
	private double rentPerMonth;
	private String pictureUrl;
	private double offer;

	public Property() {

	}

	public Property(
			int propertyId, String type, String address, String description, boolean forSale, boolean forRent,
			int numberOfRooms, int numberOfBedrooms, int numberOfBathrooms, double price, double rentPerMonth,
			String pictureUrl, double offer) {

		this.propertyId = propertyId;
		this.type = type;
		this.address = address;
		this.description = description;
		this.forSale = forSale;
		this.forRent = forRent;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBedrooms = numberOfBedrooms;
		this.numberOfBathrooms = numberOfBathrooms;
		this.price = price;
		this.rentPerMonth = rentPerMonth;
		this.pictureUrl = pictureUrl;
		this.offer = offer;
	}

	public int getPropertyId() {

		return propertyId;
	}

	public void setPropertyId(int propertyId) {

		this.propertyId = propertyId;
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public boolean isForSale() {

		return forSale;
	}

	public void setForSale(boolean forSale) {

		this.forSale = forSale;
	}

	public boolean isForRent() {

		return forRent;
	}

	public void setForRent(boolean forRent) {

		this.forRent = forRent;
	}

	public int getNumberOfRooms() {

		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {

		this.numberOfRooms = numberOfRooms;
	}

	public int getNumberOfBedrooms() {

		return numberOfBedrooms;
	}

	public void setNumberOfBedrooms(int numberOfBedrooms) {

		this.numberOfBedrooms = numberOfBedrooms;
	}

	public int getNumberOfBathrooms() {

		return numberOfBathrooms;
	}

	public void setNumberOfBathrooms(int numberOfBathrooms) {

		this.numberOfBathrooms = numberOfBathrooms;
	}

	public double getPrice() {

		return price;
	}

	public void setPrice(double price) {

		this.price = price;
	}

	public double getRentPerMonth() {

		return rentPerMonth;
	}

	public void setRentPerMonth(double rentPerMonth) {

		this.rentPerMonth = rentPerMonth;
	}

	public String getPictureUrl() {

		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {

		this.pictureUrl = pictureUrl;
	}

	public double getOffer() {

		return offer;
	}

	public void setOffer(double offer) {

		this.offer = offer;
	}
}
