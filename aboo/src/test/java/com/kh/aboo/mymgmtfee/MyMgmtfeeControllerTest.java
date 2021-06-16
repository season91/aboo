package com.kh.aboo.mymgmtfee;

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

import com.kh.aboo.mypage.mymgmtfee.model.repository.MyMgmtfeeRepository;
import com.kh.aboo.user.generation.model.vo.Generation;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })																							
public class MyMgmtfeeControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Autowired
	private MyMgmtfeeRepository myMgmtfeeRepository;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		
	}
	
	// 일반조회
	@Test
	public void myMgmtfee() throws Exception {
		Generation generation = new Generation();
		generation.setGenerationIdx("100838");
		
		this.mockMvc.perform(get("/mypage/mymgmtfee")
				.sessionAttr("generation", generation)
				).andDo(print());
		
	}
	
	//관리비 상세
	@Test
	public void myMgmtfeeDetail() throws Exception {
		Generation generation = new Generation();
		generation.setGenerationIdx("100838");
		
		this.mockMvc.perform(get("/mypage/mymgmtfee/detail")
				.sessionAttr("generation", generation)
				.param("mgmtfeeidx", "101647")
				).andDo(print());
	}
	

}