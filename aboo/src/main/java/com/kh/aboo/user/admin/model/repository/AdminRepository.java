package com.kh.aboo.user.admin.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.user.admin.model.vo.Admin;

@Mapper
public interface AdminRepository {

	
	@Select("select * from TB_ADMIN where ID = #{Id} and IS_DEL = 0")
	public Admin selectGenerationForAuth(String Id);
	
}
