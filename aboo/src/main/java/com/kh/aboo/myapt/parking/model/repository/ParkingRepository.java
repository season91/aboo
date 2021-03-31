package com.kh.aboo.myapt.parking.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.admin.car.model.vo.CarApplication;

@Mapper
public interface ParkingRepository {
	
	// [주차현황 확인]
	// 1. 전체 주차가능 대수 확인
	@Select("select apartment_parking from tb_apartment where apartment_idx = #{apartmentIdx}")
	int selectApartmentParking(String apartmentIdx);
	
	// 2. 현재 주차가능 대수 계산
	// 2-1. 현재 입차한 차량 확인
	@Select("select sum(is_in_car) from tb_car where is_del = 0")
	int selectIsInCarCnt();
	
	
	// [차량 등록 신청]
	// 1. 신청하기
	@Insert("insert into tb_car_application(application_idx, apartment_idx, generation_idx, aplct_car_number) values (sc_application_idx.nextval, #{apartmentIdx}, #{generationIdx}, #{aplctCarNumber})")
	int insertCarApplication(CarApplication carApplication);

	// 2. 신청한 내역이 있는지 확인.
	@Select("select * from tb_car_application where generation_idx = #{generationIdx} and is_process = 0")
	List<CarApplication> selectCarApplicationByGenerationIdx(String generationIdx);
	

}
