package com.kh.aboo.bdmin.management.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.aboo.bdmin.management.model.vo.ApartApplication;
import com.kh.aboo.bdmin.management.model.vo.Bdmin;
import com.kh.aboo.user.apartment.model.vo.Apartment;

@Mapper
public interface ManagementRepository {
	
	// [로그인]
	@Select("select * from tb_bdmin where id = #{id} and is_del = 0")
	Bdmin selectBdminForAuth(Bdmin bdmin);
	
	// [아파트 목록]
	// 1. 페이징 위한 개수 확인
	int selectApartCnt(Map<String, Object> searchMap);
	
	// 2. 페이징 화면 구현을 위한 list
	List<Apartment> selectApartList(Map<String, Object> searchMap);
	
	// 3. 아파트 상세
	@Select("select * from tb_apartment where apartment_idx = #{apartmentIdx}")
	Apartment selectApartment(String apartmentIdx);

	// 4. 아파트 수정
	@Update("update tb_apartment set apartment_name = #{apartmentName}, apartment_address = #{apartmentAddress}, apartment_parking = #{apartmentParking} where apartment_idx = #{apartmentIdx}")
	int updateApartment(Apartment apartment);

	// 5. 삭제는 고민 좀더해보고
	
	// [서비스신청문의 목록]
	// 1. 페이징 위한 개수 확인
	int selectApartApplicationCnt(Map<String, Object> searchMap);
	
	// 2. 페이징 화면 구현을 위한 list
	List<Apartment> selectApartApplicationList(Map<String, Object> searchMap);
	
	// 3. 서비스 신청서 상세
	@Select("select * from tb_apart_application where application_idx = #{applicationIdx}")
	ApartApplication selectApartApplication(String applicationIdx);
	
	// 4. 서비스 신청서 승인, 반려 및 아파트테이블에 새로 추가 프로시저
	void procedureApartApplicationUpdate(ApartApplication apartApplication);

	
	// [서비스 신청서]
	@Insert("insert into tb_apart_application values(SC_APART_APPLICATION_IDX.nextval, #{aApartmentName}, #{aSeparator} , #{aApartmentAddress}, #{aGenerationCnt},#{aParking}, #{aName}, #{aTell}, #{aEmail}, 0)")
	int insertApartApplication(ApartApplication apartApplication);

}
