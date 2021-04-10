package com.kh.aboo.admin.mgmtfee.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.admin.mgmtfee.model.vo.MgmtfeeOverdue;
import com.kh.aboo.user.generation.model.vo.Generation;

@Mapper
public interface MgmtfeeRepository {

	// [엑셀] 
	// 1. 관리비 DB에 넣기
	int insertMgmtfee(Mgmtfee mgmtfee);
	
	// 1-1. 삽입 전 중복되었는지 확인하기 위한 cnt 확인 문
	@Select("select count(*) from tb_mgmtfee where generation_idx = #{generationIdx} and due_date = #{dueDate} and is_del = 0")
	int selectMgmtfeeByGenerationIdxAndDueDate(Mgmtfee mgmtfee);
	
	// 1-1. 세대번호 가져오기. 아파트번호로 세대정보(동,호) 받아오기
	@Select("select building from tb_generation where apartment_idx = #{apartmentIdx} and is_del = 0 order by building asc")
	List<String> selectBuildingByApartmentIdx(String apartmentIdx);
	
	@Select("select num from tb_generation where apartment_idx = #{apartmentIdx} and is_del = 0 order by building, num  asc")
	List<String> selectNumByApartmentIdx(String apartmentIdx);

	// 2. 엑셀 양식 다운로드를 위한 세대 정보 가져온다.
	@Select("select * from TB_GENERATION where building = #{building} and num = #{num} and apartment_idx = #{apartmentIdx} and is_del = 0")
	Generation selectGenerationIdx(Generation generationInfo);
	
	// [관리비페이지 목록- 페이징,글목록 (동적쿼리)]
	// 1. 페이징을 위한 개수 확인
	int selectContentCnt(Map<String,Object> searchMap);
	
	// 2. 페이징 화면 구현을 위한 list
	List<Mgmtfee> selectMgmtfeeList(Map<String,Object> searchMap);

	// 3. 페이징에 세대정보 넣어야해서 세대번호기준 세대정보 가져온다. 관리비 상세조회에서 쓸 세대정보 조회
	@Select("select * from tb_generation where generation_idx = #{generationIdx} and is_del = 0")
	Generation selectGenerationByGenerationIdx(String generationIdx);
	
	// 3. 의 상세조회버전 동,호수로 세대정보가져온다.
	@Select("select * from tb_generation where apartment_idx = #{apartmentIdx} and building = #{building} and num = #{num}")
	Generation selectGenerationByBuildingAndNum(Generation generation);

	// [관리비 검색-미납건]
	// 1. 납기일이 지난 전체 미납 관리비 내역 조회
	@Select("select * from tb_mgmtfee where is_payment = 0 and TRUNC(DUE_DATE) < TRUNC(SYSDATE)")
	List<Mgmtfee> selectMgmtfeeListByAll();
	
	
	// [관리비 수정]
	// 1. 관리비 상세 관리비조회
	@Select("select * from tb_mgmtfee where mgmtfee_idx = #{mgmtfeeIdx} and is_del = 0")
	Mgmtfee selectMgmtfeeByMgmtfeeIdx(String mgmtfeeIdx);
	
	// 2. 관리비 상세를 위한 연체금액 확인
	@Select("select * from tb_mgmtfee_overdue where mgmtfee_idx = #{mgmtfeeIdx}")
	MgmtfeeOverdue selectMgmtfeeOverdue(String mgmtfeeIdx);
	
	// 3. 관리비 수정
	int updateMgmtfee(Mgmtfee mgmtfee);
	
	// 4.연체료 수정
	@Update("update tb_mgmtfee_overdue set overdue_fee = #{overdueFee} where mgmtfee_idx = #{mgmtfeeIdx}")
	int updateMgmtfeeOverdue(MgmtfeeOverdue mgmtfeeOverdue);
	
	// 5.수정된 관리비 납기내 납부금액 업데이트 (프로시저)
	void procedureUpdatePeriodPayment(String mgmtfeeIdx);
	
	// 6. 연체료 계산, 배치 프로시저
	void procedureMgmtOverDue(String mgmtfeeIdx);
	
	// [관리비 삭제]
	@Update("update tb_mgmtfee set is_del = 1 where mgmtfee_idx = #{mgmtfeeIdx}")
	int updateMgmtfeeIsDel(String mgmtfeeIdx);
	
	
	// 결제완료시 관리비, 연체료 결제여부 1로 업데이트한다. (구현예정)
}
