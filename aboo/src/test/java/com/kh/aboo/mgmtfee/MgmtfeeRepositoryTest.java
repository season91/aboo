package com.kh.aboo.mgmtfee;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.aboo.admin.mgmtfee.model.repository.MgmtfeeRepository;
import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.repository.AdminRepository;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MgmtfeeRepositoryTest {
	
	@Autowired
	MgmtfeeRepository mgmtfeeRepository;
	
	@Test
	public void selectGenerationIdx() {
		String generationInfo = "101d101h";
		Generation generation = mgmtfeeRepository.selectGenerationIdx(generationInfo);
		System.out.println(generation);
		System.out.println(generation.getGenerationIdx());
	}
	
	@Test
	public void insertMgmtfee() {
		Generation generation = mgmtfeeRepository.selectGenerationIdx("101d102h");
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
		
		
		int res = mgmtfeeRepository.insertMgmtfee(mgmtfee);
		System.out.println(res);
	}

}
