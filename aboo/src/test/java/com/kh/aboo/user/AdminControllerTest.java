package com.kh.aboo.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class AdminControllerTest {

	
	@Autowired
    private WebApplicationContext context;
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
	
	@Test
	//로그인 - 성공
	public void loginImpl_success() throws Exception{
		mockMvc.perform(post("/admin/loginimpl")
				.contentType(MediaType.APPLICATION_JSON)
				.param("id", "admin2")
				).andDo(print());
	}
	
	
	@Test
	//아이디 찾기 - 성공
	public void findIdImpl_success() throws Exception{
		mockMvc.perform(post("/admin/findidimpl")
				.contentType(MediaType.APPLICATION_JSON)
				.param("name", "어드민2")
				.param("email", "psuny1031@naver.com")
				).andDo(print());
		
	}
	
	
	@Test
	//비밀번호 찾기 - 성공
	public void findPasswordImpl_success() throws Exception{
		mockMvc.perform(post("/admin/findpasswordimpl")
				.contentType(MediaType.APPLICATION_JSON)
				.param("id", "admin2")
				.param("email", "psuny1031@naver.com")
				).andDo(print());	
	}
	
	
	@Test
	//세대 추가 - 성공
	public void authorityAdd_success() throws Exception{
		mockMvc.perform(post("/admin/authorityadd")
				.param("apartmentIdx", "100020")
				.param("building", "0000")
				.param("num", "0000")
				).andDo(print());	
	}
	
	@Test
	//세대원 초기화 - 성공
	public void authorityReset_success() throws Exception{
		mockMvc.perform(post("/admin/authorityreset")
				.contentType(MediaType.APPLICATION_JSON)
				.param("generationIdx", "100522")
				).andDo(print());	
	}
	
	@Test
	//세대원 삭제 - 성공
	public void authorityDelete_success() throws Exception{
		mockMvc.perform(post("/admin/authoritydelete")
				.contentType(MediaType.APPLICATION_JSON)
				.param("generationIdx", "100522")
				).andDo(print());	
	}
	
	@Test
	//개인정보 수정 업데이트- 성공
	public void modifyUpdate_success() throws Exception{
		mockMvc.perform(post("/admin/mypage/modifyupdate")
				.param("managerIdx", "100002")
				.param("password", "park1003*")
				.param("tell", "01092680961")
				.param("email", "psuny1031@naver.com")
				).andDo(print());	
	}
	
	@Test
	//개인정보 수정 이메일 인증- 성공
	public void modifyEmailImpl_success() throws Exception{
		mockMvc.perform(post("/admin/mypage/modifyemailimpl")
				.param("managerIdx", "100002")
				.param("email", "psuny1031@naver.com")
				).andDo(print());	
	}
	
	@Test
	//개인정보 수정 휴대전화 인증 - 성공
	public void modifyTellImpl_success() throws Exception{
		mockMvc.perform(post("/admin/mypage/modifytellimpl")
				.param("managerIdx", "100002")
				.param("tell", "01092680961")
				).andDo(print());	
	}
	
}
