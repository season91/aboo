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

import com.kh.aboo.user.manager.model.vo.Admin;

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
				.param("id", "admin2")
				.param("password", "park1003*")
				).andDo(print());
	}
	
	
	@Test
	//아이디 찾기 - 성공
	public void findIdImpl_success() throws Exception{
		mockMvc.perform(post("/admin/findidimpl")
				.param("name", "어드민2")
				.param("email", "psuny1031@naver.com")
				).andDo(print());
		
	}
	
	
	@Test
	//비밀번호 찾기 - 성공
	public void findPasswordImpl_success() throws Exception{
		mockMvc.perform(post("/admin/findpasswordimpl")
				.param("id", "admin2")
				.param("email", "psuny1031@naver.com")
				).andDo(print());	
	}
	
	
	@Test
	//세대 추가 - 성공
	public void authorityAdd_success() throws Exception{
		Admin admin = new Admin();
		admin.setId("admin2");
		admin.setPassword("park1003*");
		admin.setApartmentIdx("100020");
		admin.setEmail("psuny1031@naver.com");
		admin.setName("어드민2");
		admin.setManagerIdx("100002");		
		
		mockMvc.perform(post("/admin/authorityadd")
				.sessionAttr("admin",admin)
				.param("apartmentIdx", "100020")
				.param("building", "0000")
				.param("num", "0000")
				).andDo(print());	
	}
	
	@Test
	//세대원 초기화 - 성공
	public void authorityReset_success() throws Exception{
		Admin admin = new Admin();
		admin.setId("admin2");
		admin.setPassword("park1003*");
		admin.setApartmentIdx("100020");
		admin.setEmail("psuny1031@naver.com");
		admin.setName("어드민2");
		admin.setManagerIdx("100002");	
		
		mockMvc.perform(post("/admin/authorityreset")
				.sessionAttr("admin",admin)
				.param("generationIdx", "100522")
				).andDo(print());	
	}
	
	@Test
	//세대원 삭제 - 성공
	public void authorityDelete_success() throws Exception{
		Admin admin = new Admin();
		admin.setId("admin2");
		admin.setPassword("park1003*");
		admin.setApartmentIdx("100020");
		admin.setEmail("psuny1031@naver.com");
		admin.setName("어드민2");
		admin.setManagerIdx("100002");	
		
		mockMvc.perform(post("/admin/authoritydelete")
				.sessionAttr("admin",admin)
				.param("generationIdx", "100522")
				).andDo(print());	
	}
	
	@Test
	//개인정보 수정 업데이트- 성공
	public void modifyUpdate_success() throws Exception{
		Admin admin = new Admin();
		admin.setId("admin2");
		admin.setPassword("park1003*");
		admin.setApartmentIdx("100020");
		admin.setEmail("psuny1031@naver.com");
		admin.setName("어드민2");
		admin.setManagerIdx("100002");	
		
		mockMvc.perform(post("/admin/mypage/modifyupdate")
				.sessionAttr("admin",admin)
				.param("managerIdx", "100002")
				.param("password", "park1003*")
				.param("tell", "01092680961")
				.param("email", "psuny1031@naver.com")
				).andDo(print());	
	}
	
	@Test
	//개인정보 수정 이메일 인증- 성공
	public void modifyEmailImpl_success() throws Exception{
		Admin admin = new Admin();
		admin.setId("admin2");
		admin.setPassword("park1003*");
		admin.setApartmentIdx("100020");
		admin.setEmail("psuny1031@naver.com");
		admin.setName("어드민2");
		admin.setManagerIdx("100002");	
		
		mockMvc.perform(post("/admin/mypage/modifyemailimpl")
				.sessionAttr("admin",admin)
				.param("managerIdx", "100002")
				.param("email", "psuny1031@naver.com")
				).andDo(print());	
	}
	
	@Test
	//개인정보 수정 휴대전화 인증 - 성공
	public void modifyTellImpl_success() throws Exception{
		Admin admin = new Admin();
		admin.setId("admin2");
		admin.setPassword("park1003*");
		admin.setApartmentIdx("100020");
		admin.setEmail("psuny1031@naver.com");
		admin.setName("어드민2");
		admin.setManagerIdx("100002");
		
		mockMvc.perform(post("/admin/mypage/modifytellimpl")
				.sessionAttr("admin",admin)
				.param("managerIdx", "100002")
				.param("tell", "01092680961")
				).andDo(print());	
	}
	
}
