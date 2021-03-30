package com.kh.aboo.mypage.mymgmtfee.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.admin.mgmtfee.model.vo.MgmtfeeOverdue;
import com.kh.aboo.mypage.mymgmtfee.model.vo.MgmtfeePayment;
import com.kh.aboo.user.generation.model.vo.Generation;

@Mapper
public interface MyMgmtfeeRepository {
	
	// [관리비 페이지 페이징처리]
	// 1. total 개수 세는 쿼리
	@Select("select count(*) from tb_mgmtfee where generation_idx = #{generationIdx}")
	int selectContentCnt(String generationIdx);

	// 2. 관리비 리스트 가져오는 쿼리
	List<Mgmtfee> selectMyMgmtfeeList(Map<String, Object> commandMap);
	
	// 3. 연체료 가져오는 쿼리
	@Select("select * from tb_mgmtfee_overdue where mgmtfee_idx = #{mgmtfeeIdx}")
	MgmtfeeOverdue selectMyMgmtfeeOverdue(String mgmtfeeIdx);
		
	// [관리비 상세 페이지]
	// 1. 관리비번호로 관리비 조회하는 쿼리
	@Select("select * from tb_mgmtfee where mgmtfee_idx =  #{mgmtfeeIdx}")
	Mgmtfee selectMyMgmtfeeByMgmtfeeIdx(String mgmtfeeIdx);
	
	// 2. 고지월로만 조회
	@Select("select extract(year from mgmt_start_date) year, extract(month from mgmt_start_date) month from tb_mgmtfee where mgmtfee_idx=#{mgmtfeeIdx}")
	Map<String,Object> selectMyMgmtfeeDate(String mgmtfeeIdx);
	
	//선영 결제
	@Insert("insert into TB_MGMTFEE_PAYMENT(PAYMENT_IDX,MGMTFEE_IDX,PAYMENT_METHOD,PAYMENT_AMOUNT,PAYMENT_STATE) values(SC_PAYMENT_IDX.nextval,#{mgmtfeeIdx},#{paymentMethod},#{paymentAmount},'1')")
	int insertPayment(MgmtfeePayment mgmtfeePayment);
}
