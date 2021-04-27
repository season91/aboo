package com.kh.aboo.schedule;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.kh.aboo.admin.schedule.model.vo.Schedule;
import com.kh.aboo.board.info.model.vo.InfoBoard;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class scheduleControllerTest {
	
	@Autowired
    private WebApplicationContext context;
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
	
	@Test
	public void schedule() throws Exception{
		
		Admin admin = new Admin();
		admin.setApartmentIdx("100020");
		
		Schedule schedule = new Schedule();
		schedule.setScheduleSdate(java.sql.Date.valueOf("2021-04-28"));
		schedule.setScheduleEdate(java.sql.Date.valueOf("2021-05-01"));
		schedule.setScheduleCon("아파트 도색작업");
		
		mockMvc.perform(post("/admin/schedule/addschedule")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.sessionAttr("admin", admin)
				).andDo(print());
	}

}
