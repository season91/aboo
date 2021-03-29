package com.kh.aboo.user.manager.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;

@Mapper
public interface AdminRepository {

	
	@Select("select * from TB_MANAGER where ID = #{Id} and IS_DEL = 0")
	public Admin selectAdminForAuth(String Id);
	
	@Insert("insert into TB_MANAGER(MANAGER_IDX,APARTMENT_IDX,ID,PASSWORD,NAME,EMAIL,TELL,BIRTH) values(SC_MANAGER_IDX.nextval,'100000',#{id},#{password},#{name},#{email},#{tell},#{birth})")
	public int insertAdmin(Admin admin);
	
	@Insert("insert into TB_GENERATION(GENERATION_IDX,APARTMENT_IDX,ID,PASSWORD,BUILDING,NUM) values(SC_GENERATION_IDX.nextval,#{apartmentIdx},#{id},#{password},#{building},#{num})")
	public int insertGeneration(Generation generation);
	
	@Select("select count(*) from TB_GENERATION where is_del = 0 and APARTMENT_IDX = #{apartmentIdx}")
	int selectContentCnt(String apartmentIdx);

	//세대 추가 페이지 리스트
	List<Generation> selectAuthorityList(Map<String,Object> authorityMap);
		
	//아이디 메일 전에 있는 어드민인지 체크
	@Select("select * from TB_MANAGER where name = #{name} and EMAIL = #{email}")
	public Admin selectFindId(Admin admin);
	
	//비밀번호 메일보내기 전에 있는 어드민인지 체크
	@Select("select * from TB_MANAGER where email = #{email} and id = #{id}")
	public Admin selectFindPassword(Admin admin);
	
	//비밀번호 변경
	@Select("update TB_MANAGER set PASSWORD = #{password} where ID = #{id}")
	public void updateFindPassword(Admin admin);
	
	//어드민 정보불러오기
	@Select("select * from TB_MANAGER where MANAGER_IDX = #{managerIdx}")
	Admin selectAdmin(Admin admin);
	
	//정보 업데이트
	int updateAdminModify(Admin admin);
	
	//이메일인증 업데이트 
	@Update("update TB_MANAGER set email = #{email} where MANAGER_IDX = #{managerIdx}")
	int updateAdminEmail(Admin admin);
	
	//세대 초기화
	@Update("update TB_GENERATION set IS_DEL = 1 where GENERATION_IDX =#{generationIdx}")
	int resetGeneration(Generation generation);
	
	//세대 초기화 후 새로 생성
	@Insert("@Insert into TB_GENERATION(GENERATION_IDX,APARTMENT_IDX,BUILDING,NUM,ID,PASSWORD) value(SC_GENERATION_IDX.nextval,#{apartmentIdx},#{building},${num},#{id},#{password})")
	int resetInsertGeneration(Generation generation);
	
}	
