package com.kh.aboo.bdmin;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.aboo.bdmin.management.model.repository.ManagementRepository;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class ManagementRepositoryTest {

	@Autowired
	ManagementRepository managementRepository;
	
	@Test
	public void selectAdminCnt() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchType", "name");
		
		System.out.println(managementRepository.selectAdminCnt(map));
				
	}
}
