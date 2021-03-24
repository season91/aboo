package com.kh.aboo.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class GenerationControllerTest {

	
	@Autowired
    private WebApplicationContext context;
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
	
	// 세대 더미 추가할 테스트 메서드
	@Test
	public void generationAddTest() throws Exception {
//		mockMvc.perform(get("/generation/add")
//				.param("id", "101d101h")
//				.param("password", "123")
//				.param("aprtmentIdx", "100000")
//				).andDo(print());
		
		// 101~109동부터 1호~6호까지
		for (int i = 1; i < 10; i++) {
			String a = "10"+i;
			System.out.println(a);
			for (int j = 1; j < 7; j++) {
				String b = i + "0" + j;
				System.out.println(a+b);
				mockMvc.perform(get("/generation/add")
		                  .param("id", a+"d"+b+"h")
		                  .param("password", "123")
		                  .param("apartmentIdx", "100000")
		                  .param("building", a)
		                  .param("num", b)
		                  ).andDo(print());
			}
			
		}
	}
	
}
