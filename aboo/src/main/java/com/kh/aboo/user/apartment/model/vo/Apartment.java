package com.kh.aboo.user.apartment.model.vo;

public class Apartment {

	private String apartmentIdx;
	private String apartmentName;
	private String apartmentAddress;
	private String apartmentLat;
	private String apartmentLng;
	private String parking;

	public Apartment() {

	}

	public Apartment(String apartmentIdx, String apartmentName, String apartmentAddress, String apartmentLat,
			String apartmentLng, String parking) {
		super();
		this.apartmentIdx = apartmentIdx;
		this.apartmentName = apartmentName;
		this.apartmentAddress = apartmentAddress;
		this.apartmentLat = apartmentLat;
		this.apartmentLng = apartmentLng;
		this.parking = parking;
	}

	public String getApartmentIdx() {
		return apartmentIdx;
	}

	public void setApartmentIdx(String apartmentIdx) {
		this.apartmentIdx = apartmentIdx;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getApartmentAddress() {
		return apartmentAddress;
	}

	public void setApartmentAddress(String apartmentAddress) {
		this.apartmentAddress = apartmentAddress;
	}

	public String getApartmentLat() {
		return apartmentLat;
	}

	public void setApartmentLat(String apartmentLat) {
		this.apartmentLat = apartmentLat;
	}

	public String getApartmentLng() {
		return apartmentLng;
	}

	public void setApartmentLng(String apartmentLng) {
		this.apartmentLng = apartmentLng;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	@Override
	public String toString() {
		return "Apartment [apartmentIdx=" + apartmentIdx + ", apartmentName=" + apartmentName + ", apartmentAddress="
				+ apartmentAddress + ", apartmentLat=" + apartmentLat + ", apartmentLng=" + apartmentLng + ", parking="
				+ parking + "]";
	}

}
