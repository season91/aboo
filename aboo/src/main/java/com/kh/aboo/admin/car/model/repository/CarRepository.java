package com.kh.aboo.admin.car.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.aboo.admin.car.model.vo.Car;
import com.kh.aboo.admin.car.model.vo.CarApplication;
import com.kh.aboo.user.generation.model.vo.Generation;

@Mapper
public interface CarRepository {

	// test qr확인
	@Select("select * from tb_car where car_idx = #{carIdx}")
	Car selectCar(String carIdx);
	
	// test qr제목확인
	@Select("select * from tb_car where generation_idx = #{generationIdx} and car_number = #{carNumber} and apartment_idx = #{apartmentIdx}")
	Car selectCarByGenerationIdxAndCarNumber(Car car);
	
	// [QR코드]
	// 1. DB에 넣어주기
	@Insert("insert into tb_car(car_idx, apartment_idx, generation_idx, car_number, car_qr) values (sc_car_idx.nextval, #{apartmentIdx}, #{generationIdx}, #{carNumber}, #{carQR}||'c'||sc_car_idx.currval||'.jpg')")
	int insertCar(Car car);
	
	// 2. 넣기전에 이 세대 2건이상 있는지 확인한다.
	@Select("select count(*) from tb_car where generation_idx = #{generationIdx} and is_del = 0")
	int selectCarCnt(String generationIdx);
	
	// 3. 넣기전에 차량번호와 세대관리번호로 등록차량조회하기.
	@Select("select * from tb_car where car_number= #{carNumber} and generation_idx = #{generationIdx} and is_del = 0")
	Car selectCarByCarNumberAndGenerationIdx(Map<String, String> commandMap);
	
	// [차량 등록]
	// 1. 차량등록을 위해 입력받은 동,호수정보로 세대정보 가져오기
	@Select("select * from tb_generation where building = #{building} and num = #{num} and is_del = 0")
	Generation selectGenerationByBuildingAndNum(Generation generation);
	
	// [차량목록- 페이징, 글목록(동적쿼리)]
	// 1. 페이징 위한 개수 확인, 삭제내역도 가져온다.
	int selectContentCnt(Map<String,Object> searchMap);
	
	// 2. 페이징 화면 구현을 위한 list
	List<Car> selectCarList(Map<String,Object> searchMap);
	
	// 3. 페이징에 세대정보 넣어야해서 세대정보 가져온다
	@Select("select * from tb_generation where generation_idx = #{generationIdx}")
	Generation selectGenerationByGenerationIdx(String generationIdx);
	
	// [차량등록 신청 - 페이징, 글목록]
	// 1. 페이징 위한 총 개수 확인
	int selectApplicationContentCnt(Map<String,Object> applicationMap);
	
	// 2.페이징 화면 구현을 위한 list
	List<CarApplication> selectCarApplicationList(Map<String,Object> applicationMap);
	
	// [차량 등록 신청 승인]
	// 1. 차량 승인시 처리상태 바꿔준다.
	@Update("update tb_car_application set is_process = 1 where application_idx = #{applicationIdx}")
	int updateCarApplicationApproval(String applicationIdx);
	
	// 2. 차량신청 번호로 객체불러온다
	@Select("select * from tb_car_application where application_idx = #{applicationIdx}")
	CarApplication selectCarApplication(String applicationIdx);
	
	// 3. 차량 반려시 처리상태 바꿔준다.
	@Update("update tb_car_application set is_process = 2 where application_idx = #{applicationIdx}")
	int updateCarApplicationReject(String applicationIdx);
	
	// [차량수정]
	// 1. QR코드 수정보단 새발급을 해야하므로 차량 번호만 수정하게 한다.
	@Update("update tb_car set car_NUMBER = #{carNumber} where car_idx = #{carIdx} and is_del = 0 ")
	int updateCar(Car car);
	
	// [차량삭제]
	@Update("update tb_car set is_del = 1 where car_idx = #{carIdx}")
	int deleteCar(String carIdx);
	
	// [차량 입차, 출차 확인 후 상태값 변경해주기.]
	// 1. 진행전 세대관리번호와 차량번호가 일치하는지 확인한다.
	@Select("select car_idx from tb_car where generation_idx = #{generationIdx} and is_del = 0")
	List<String> selectCarNumberByGenerationIdx(String generationIdx);
	
	// 프로시저 사용한다.
	void procedureIsInCarUpdate(String carIdx);
}
