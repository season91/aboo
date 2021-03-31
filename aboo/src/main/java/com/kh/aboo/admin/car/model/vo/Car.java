package com.kh.aboo.admin.car.model.vo;

public class Car {
	private String carIdx; // 차량관리번호
	private String apartmentIdx; // 아파트번호
	private String generationIdx; //세대관리번호
	private String carNumber; //차량번호
	private String carQR; //qr이미지
	private int isInCar; // 입출차여부
	private int isDel; // 삭제여부

	
	public String getCarIdx() {
		return carIdx;
	}
	public void setCarIdx(String carIdx) {
		this.carIdx = carIdx;
	}
	public String getApartmentIdx() {
		return apartmentIdx;
	}
	public void setApartmentIdx(String apartmentIdx) {
		this.apartmentIdx = apartmentIdx;
	}
	public String getGenerationIdx() {
		return generationIdx;
	}
	public void setGenerationIdx(String generationIdx) {
		this.generationIdx = generationIdx;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarQR() {
		return carQR;
	}
	public void setCarQR(String carQR) {
		this.carQR = carQR;
	}
	public int getIsInCar() {
		return isInCar;
	}
	public void setIsInCar(int isInCar) {
		this.isInCar = isInCar;
	}


	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	@Override
	public String toString() {
		return "Car [carIdx=" + carIdx + ", apartmentIdx=" + apartmentIdx + ", generationIdx=" + generationIdx
				+ ", carNumber=" + carNumber + ", carQR=" + carQR + ", isInCar=" + isInCar + ", isDel=" + isDel + "]";
	}
	
	


}
