package com.kh.aboo.user;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.aboo.user.admin.model.repository.AdminRepository;
import com.kh.aboo.user.admin.model.vo.Mgmtfee;
import com.kh.aboo.user.generation.model.vo.Generation;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class AdminRepositoryTest {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Test
	public void selectGenerationIdx() {
		String generationInfo = "101d101h";
		Generation generation = adminRepository.selectGenerationIdx(generationInfo);
		System.out.println(generation);
		System.out.println(generation.getGenerationIdx());
	}
	
	@Test
	public void insertMgmtfee() {
		Generation generation = adminRepository.selectGenerationIdx("101d102h");
		String generationIdx = generation.getGenerationIdx();
		
		Mgmtfee mgmtfee = Mgmtfee.builder()
				.generationIdx(generationIdx)
				.gnrlMgmtFee("111")
				.cleanFee("222")
				.elvtrMnfee("333")
				.genElctr("444")
				.comonElctr("455")
				.genWater("666")
				.sewer("777")
				.expenses("888")
				.genReduction("999")
				.periodPayment("1111")
				.dueDate(Date.valueOf("2021-03-01"))
				.mgmtStartDate(Date.valueOf("2021-03-01"))
				.mgmtEndDate(Date.valueOf("2021-03-01"))
				.mgmtWriteDate(Date.valueOf("2021-03-01"))
				.build();
		System.out.println(mgmtfee);
		
		
		int res = adminRepository.insertMgmtfee(mgmtfee);
		System.out.println(res);
	}

}
