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

import com.kh.aboo.user.generation.model.service.GenerationService;
import com.kh.aboo.user.generation.model.vo.Generation;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class GenerationControllerTest {

	
	@Autowired
    private WebApplicationContext context;
	private MockMvc mockMvc;
	private GenerationService generationService;
	
	
	@Before
    public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
      
    }
	
	
	@Test
	//로그인 - 성공
	public void loginImpl_success() throws Exception{
		mockMvc.perform(post("/loginimpl")
				.param("id","grf000d000h")
				.param("password","123")
				).andDo(print());
	}
	
	
	@Test
	//아이디 찾기 - 성공
	public void findIdImpl_success() throws Exception{
		mockMvc.perform(post("/findidimpl")
				.param("building", "000")
				.param("num", "000")
				.param("email", "psuny1031@naver.com")
				).andDo(print());
		
	}
	
	
	@Test
	//비밀번호 찾기 - 성공
	public void findPasswordImpl_success() throws Exception{
		mockMvc.perform(post("/findpasswordimpl")
				.param("id", "grf000d000h")
				.param("email", "psuny1031@naver.com")
				).andDo(print());	
	}
	
	
	@Test
	//세대원 수정 - 성공
	public void generationWonModify_success() throws Exception{
		Generation generationInfo = new Generation();
		generationInfo.setApartmentIdx("100000");
		mockMvc.perform(post("/mypage/generationwonmodify")
				.sessionAttr("generationInfo", generationInfo)
				.param("generationWonIdx", "100304")
				.param("name", "박선영")
				.param("tell", "01092680961")
				).andDo(print());	
	}
	
	@Test
	//세대원 삭제 - 성공
	public void generationWonDelete_success() throws Exception{
		mockMvc.perform(post("/mypage/generationwondelete")
				.contentType(MediaType.APPLICATION_JSON)
				.param("generationWonIdx", "100304")
				).andDo(print());	
	}
	
	@Test
	//세대원 추가 - 성공
	public void generationWonAdd_success() throws Exception{
		mockMvc.perform(post("/mypage/generationwonadd")
				.contentType(MediaType.APPLICATION_JSON)
				.param("generationIdx", "100522")
				.param("name", "박선영")
				.param("tell", "01092680961")

				).andDo(print());	
	}
	
	@Test
	//개인정보 수정 업데이트- 성공
	public void modifyUpdate_success() throws Exception{
		mockMvc.perform(post("/mypage/modifyupdate")
				.param("generationIdx", "100522")
				.param("password", "park1003*")
				.param("tell", "01092680961")
				.param("email", "psuny1031@naver.com")
				).andDo(print());	
	}
	
	@Test
	//개인정보 수정 이메일 인증- 성공
	public void modifyEmailImpl_success() throws Exception{
		mockMvc.perform(post("/mypage/modifyemailimpl")
				.param("generationIdx", "100522")
				.param("email", "psuny1031@naver.com")
				).andDo(print());	
	}
	
	@Test
	//개인정보 수정 휴대전화 인증 - 성공
	public void modifyTellImpl_success() throws Exception{
		mockMvc.perform(post("/mypage/modifytellimpl")
				.param("generationIdx", "100522")
				.param("tell", "01092680961")
				).andDo(print());	
	}
	
	
	// 세대 더미 추가할 테스트 메서드
/*	@Test
	public void generationAddTest() throws Exception {
		
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 4; j++) {
				
				mockMvc.perform(get("/generation/add")
						.param("id", "bxi"+"102d"+i+"0"+j+"h")
						.param("password", "123")
						.param("apartmentIdx", "100020")
						.param("building", "102")
						.param("num", i+"0"+j)
						).andDo(print());
			}
		}
	}
	
	*/
	
}
