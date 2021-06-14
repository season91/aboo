package com.kh.aboo.mgmtfee;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.apache.commons.collections4.MultiValuedMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.kh.aboo.admin.car.model.vo.CarApplication;
import com.kh.aboo.admin.mgmtfee.model.repository.MgmtfeeRepository;
import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })																							
public class MgmtfeeControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Autowired
	private MgmtfeeRepository mgmtfeeRepository;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		
	}
	
	//일반조회
	@Test
	public void adminMgmtfee() throws Exception {
		Admin admin = new Admin();
		admin.setApartmentIdx("100020");
		
		mockMvc.perform(get("/admin/mgmtfee")
				.sessionAttr("admin", admin)
				).andDo(print());
	}
	
	// 관리비 삭제
	@Test
	public void mgmtfeeDelete() throws Exception {
		//매개변수 관리비번호
		this.mockMvc.perform(get("/admin/mgmtfeedelete")
				.param("mgmtfeeidx", "102723")
				).andDo(print());
		
	}
	
	//관리비 수정
	@Test
	public void mgmtfeeModifyImpl() throws Exception {
		
		Admin admin = new Admin();
		admin.setApartmentIdx("100020");
		
		//매개변수 관리비,연체료, 납부상태
		Mgmtfee mgmtfeeTest = mgmtfeeRepository.selectMgmtfeeByMgmtfeeIdx("102724");
		
		MultiValueMap<String, String> mgmtfee = new LinkedMultiValueMap<String, String>();
		mgmtfee.add("mgmtfeeIdx", mgmtfeeTest.getMgmtfeeIdx());
		mgmtfee.add("gnrlMgmtFee", mgmtfeeTest.getGnrlMgmtFee());
		mgmtfee.add("cleanFee", mgmtfeeTest.getCleanFee());
		mgmtfee.add("elvtrMnfee", mgmtfeeTest.getElvtrMnfee());
		mgmtfee.add("genElctr", mgmtfeeTest.getGenElctr());
		mgmtfee.add("comonElctr", mgmtfeeTest.getComonElctr());
		mgmtfee.add("genWater", mgmtfeeTest.getGenWater());
		mgmtfee.add("sewer", mgmtfeeTest.getSewer());
		mgmtfee.add("expenses", mgmtfeeTest.getExpenses());
		mgmtfee.add("genReduction", mgmtfeeTest.getGenReduction());
		mgmtfee.add("generationIdx", mgmtfeeTest.getGenerationIdx());
		mgmtfee.add("mgmtStartDate", mgmtfeeTest.getMgmtStartDate().toString());
		mgmtfee.add("mgmtEndDate", mgmtfeeTest.getMgmtEndDate().toString());
		mgmtfee.add("mgmtWriteDate", mgmtfeeTest.getMgmtWriteDate().toString());
		
		
		
		this.mockMvc.perform(post("/admin/mgmtfee/modifyimpl")
				.sessionAttr("admin", admin)
				.params(mgmtfee)
				.param("overdueFee", "500")
				.param("isPaymentText", "납부")
				).andDo(print());
		
	}

}