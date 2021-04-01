package com.kh.aboo.mymgmtfee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.mypage.mymgmtfee.model.repository.MyMgmtfeeRepository;
import com.kh.aboo.mypage.mymgmtfee.model.vo.MgmtfeePayment;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MyMgmtfeeRepositoryTest {
	
	@Autowired
	MyMgmtfeeRepository myMgmtfeeRepository;
	
	/* @Test */
	public void selectMyMgmtfeeList() {
		Map<String, Object> generationMap = new HashMap<>();
		String generationIdx = "100296";
		Paging paging = Paging.builder()
				.currentPage(1)
				.blockCnt(5)
				.cntPerPage(5)
				.type("board")
				.total(myMgmtfeeRepository.selectContentCnt(generationIdx))
				.build();
		System.out.println(paging.toString());
		generationMap.put("paging", paging);
		generationMap.put("generationIdx", generationIdx);
		
		List<Mgmtfee> myMgmtfeeList = myMgmtfeeRepository.selectMyMgmtfeeList(generationMap);
		System.out.println(myMgmtfeeList);
	}
	

	@Test
	public void procedurePaymentInsert() {
		MgmtfeePayment mgmtfeePayment = new MgmtfeePayment();
		
		mgmtfeePayment.setMgmtfeeIdx("100970");
		mgmtfeePayment.setPaymentMethod("card");
		mgmtfeePayment.setPaymentAmount("123");
		
		myMgmtfeeRepository.procedurePaymentInsert(mgmtfeePayment);
				
	}
}
