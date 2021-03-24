package com.kh.aboo.user.admin.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.user.admin.model.vo.Admin;
import com.kh.aboo.user.generation.model.vo.Generation;

@Mapper
public interface AdminRepository {

	
	@Select("select * from TB_MANAGER where ID = #{Id} and IS_DEL = 0")
	public Admin selectGenerationForAuth(String Id);
	
	@Insert("insert into TB_MANAGER(PARTNER_IDX,APARTMENT_IDX,ID,PASSWORD,NAME,EMAIL,TELL,BIRTH) values(SC_MANAGER_IDX.nextval,'100000',#{id},#{password},#{name},#{email},#{tell},#{birth})")
	public int insertAdmin(Admin admin);
	
	@Insert("insert into TB_GENERATION(GENERATION_IDX,APARTMENT_IDX,ID,PASSWORD,BUILDING,NUM) values(SC_GENERATION_IDX.nextval,#{apartmentIdx},#{id},#{password},#{building},#{num})")
	public int insertGeneration(Generation generation);
	
	@Select("select count(*) from TB_GENERATION where is_del = 0 and APARTMENT_IDX = #{apartmentIdx}")
	int selectContentCnt(String apartmentIdx);
	
	List<Generation> selectauthorityList(Map<String,Object> authorityMap);
}	
