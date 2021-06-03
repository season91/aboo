package com.kh.aboo.car;

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

import com.kh.aboo.admin.car.model.vo.CarApplication;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })																							
public class CarControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		
	}
	
	//차량신청조회
	@Test
	public void carApplication() throws Exception {
		//매개변수 Admin세션
		Admin admin = new Admin();
		admin.setApartmentIdx("100020");
		
		this.mockMvc.perform(get("/admin/car/application")
				.sessionAttr("admin", admin)
				).andDo(print());
		
	}
	
	// 차량등록
	@Test
	public void carAdd() throws Exception {
		Admin admin = new Admin();
		admin.setApartmentIdx("100020");
		
		String building = "101";
		String carNumber = "130마18";
		
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 5; j++) {
				this.mockMvc.perform(get("/admin/caradd")
						.sessionAttr("admin", admin)
						.param("building", building)
						.param("num", i+"0"+j)
						.param("carNumber", carNumber+i+j)
						).andDo(print());
				
			}
			
		}
		
	}
	
	// 입차일부
	@Test
	public void carread() throws Exception {
		String generationIdx = "10089";
		String carNumber = "10028";
		for (int i = 0, j=7; i < 5; i++,j++) {
			this.mockMvc.perform(get("/admin/carread")
					.param("generationidx", generationIdx+i)
					.param("caridx", carNumber+j)
					).andDo(print());
		}
	}
	
	//차량신청
	@Test
	public void carApplicationImpl() throws Exception {
		Generation generation = new Generation();
		generation.setApartmentIdx("100020");
		

		for (int i = 0; i < 8; i++) {
			generation.setGenerationIdx("10086"+i);
			this.mockMvc.perform(get("/myapt/parking/applicationimpl")
					.sessionAttr("generation", generation)
					.param("aplctCarNumber", "133사700"+i)
					).andDo(print());
		}
		
	}

}