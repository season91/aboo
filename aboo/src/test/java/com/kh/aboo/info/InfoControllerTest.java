package com.kh.aboo.info;

import java.sql.Date;

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

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.board.info.model.repository.InfoRepository;
import com.kh.aboo.board.info.model.vo.InfoBoard;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class InfoControllerTest {
	
	@Autowired
    private WebApplicationContext context; 
    private MockMvc mockMvc;

    @Before
    public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
	
	@Test
	public void insertInfoBoard() {

//		mockMvc.perform(get("/generation/add")
//		.param("id", "101d101h")
//		.param("password", "123")
//		.param("aprtmentIdx", "100000")
//		).andDo(print());

	}

	

}
