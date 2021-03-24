package com.kh.aboo.user.admin.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.user.admin.model.vo.Admin;
import com.kh.aboo.user.admin.model.vo.Mgmtfee;
import com.kh.aboo.user.generation.model.vo.Generation;

@Mapper
public interface AdminRepository {

	
	@Select("select * from TB_MANAGER where ID = #{Id} and IS_DEL = 0")
	public Admin selectGenerationForAuth(String Id);
	
	@Insert("insert into TB_MANAGER(PARTNER_IDX,APARTMENT_IDX,ID,PASSWORD,NAME,EMAIL,TELL,BIRTH) values(SC_MANAGER_IDX.nextval,'100000',#{id},#{password},#{name},#{email},#{tell},#{birth})")
	public int insertAdmin(Admin admin);
	
	// 관리비 DB 넣기 위한 세대관리번호 
	@Select("select * from TB_GENERATION where id = #{generationInfo}")
	public Generation selectGenerationIdx(String generationInfo);
	
	// 관리비 DB에 넣기
	public int insertMgmtfee(Mgmtfee mgmtfee);
	
	// 엑셀 양식 다운로드를 위한 세대전체정보
	@Select("select * from tb_generation where apartment_idx = #{apartmentIdx}")
	public List<Generation> selectGeneration();
	
}	
