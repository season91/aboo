package com.kh.aboo.mypage.mycar.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.admin.car.model.vo.Car;

@Mapper
public interface MyCarRepository {
	// [등록 차량 조회]
	// 1. 등록된 차량 정보 조회. 최대2개가 나올 수 있어 List로 받는다.
	@Select("select * from tb_car where generation_idx = #{generationIdx} and is_del = 0")
	List<Car> selectCarByGenerationIdx(String generationIdx);

}
