package com.kh.aboo.member.model.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.member.model.vo.Member;

//@Repository : 원래 다오기능하는애 한테 어노테이션 붙여주는데 database에서 발생하는 SqlException을 DataAccessException으로 wrapping 한다.
// Mapper를 쓰면서 안쓴거임.
@Mapper // 여기서 발생하는 예외들을 전부 DataAccessException로 감싸주는 역할을 하기 때문. 그래서 Repository안달아도 작동하는 것 임.
public interface MemberRepository {
	
	// 아이디로 회원을 조회해서 나온 결과를 Member 객체에 매핑
	@Select("SELECT * FROM tb_member WHERE user_id = #{userId}")
	Member selectMemberById(String userId);
	
	// 메일, 퓨대혼번호 중복 확인
	@Select("select count(*) from tb_member where email = #{email}")
	int selectMemberByEmail(String email);
	
	@Select("select count(*) from tb_member where tell = #{tell}")
	int selectMemberByTell(String tell);
	
	// 회원가입
	@Insert("insert into tb_member(user_id, password, email, tell) values(#{userId},#{password},#{email},#{tell})")
	int insertMember(Member member);
	
	// 로그인 인증을 위한 회원 정보, 탈퇴 안한사람 정보만
	@Select("select * from tb_member where user_id = #{userId} and is_leave = 0")
	Member selectMemberForAuth(String userId);
	
	
}
