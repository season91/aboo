package com.kh.aboo.user.apartment.model.vo;

public class Apartment {

	private String apartmentIdx;
	private String apartmentName;
	private String apartmentAddress;
	private String apartmentLat;
	private String apartmentLng;
	private String apartmentParking;
	private String separator;

	public Apartment() {

	}

	public Apartment(String apartmentIdx, String apartmentName, String apartmentAddress, String apartmentLat,
			String apartmentLng, String apartmentParking, String separator) {
		super();
		this.apartmentIdx = apartmentIdx;
		this.apartmentName = apartmentName;
		this.apartmentAddress = apartmentAddress;
		this.apartmentLat = apartmentLat;
		this.apartmentLng = apartmentLng;
		this.apartmentParking = apartmentParking;
		this.separator = separator;
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

	public String getApartmentParking() {
		return apartmentParking;
	}

	public void setApartmentParking(String apartmentParking) {
		this.apartmentParking = apartmentParking;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	@Override
	public String toString() {
		return "Apartment [apartmentIdx=" + apartmentIdx + ", apartmentName=" + apartmentName + ", apartmentAddress="
				+ apartmentAddress + ", apartmentLat=" + apartmentLat + ", apartmentLng=" + apartmentLng
				+ ", apartmentParking=" + apartmentParking + ", separator=" + separator + "]";
	}

}
