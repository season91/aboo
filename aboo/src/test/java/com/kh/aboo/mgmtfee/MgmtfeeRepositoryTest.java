package com.kh.aboo.mgmtfee;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.aboo.admin.mgmtfee.model.repository.MgmtfeeRepository;
import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.user.generation.model.vo.Generation;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MgmtfeeRepositoryTest {
	
	@Autowired
	MgmtfeeRepository mgmtfeeRepository;
	
	 @Test
	   public void selectMemberById() {
		   String apartmentIdx = "100000";
		   List<String> test = mgmtfeeRepository.selectBuildingByApartmentIdx(apartmentIdx);
		   List<String> test2 = mgmtfeeRepository.selectNumByApartmentIdx(apartmentIdx);
		   for (int i = 0; i < test.size(); i++) {
			System.out.println(test.get(i)+"동");
			System.out.println(test2.get(i)+"호");
		   }
	   }
	   
	   @Test
	   public void selectGenerationIdx() {
		   String building = "101";
		   String num = "101";
		   String apartmentIdx = "100000";
		   Generation inpu = new Generation();
		   inpu.setBuilding(building);;
		   inpu.setNum(num);
		   inpu.setApartmentIdx(apartmentIdx);
		   Generation test = mgmtfeeRepository.selectGenerationIdx(inpu);
		   System.out.println(test);
	   }
	   
	   @Test
	   public void procedureUpdatePeriodPayment() {
		   String fee = "40000";
		   String mgmtfeeIdx = "100971";
		   Mgmtfee mgmtfee = new Mgmtfee();
		   mgmtfee.setMgmtfeeIdx(mgmtfeeIdx);
		   mgmtfee.setElvtrMnfee(fee);
		   mgmtfee.setGenElctr(fee);
		   mgmtfee.setCleanFee(fee);
		   mgmtfee.setComonElctr(fee);
		   mgmtfee.setGenWater(fee);
		   mgmtfee.setSewer(fee);
		   mgmtfee.setExpenses(fee);
		   mgmtfee.setGenReduction(fee);
		   mgmtfee.setGnrlMgmtFee(fee);
		   mgmtfee.setExpenses(fee);
		   //업데이트문
		   mgmtfeeRepository.updateMgmtfee(mgmtfee);
		   
		   //프로시저부른다.
		   mgmtfeeRepository.procedureUpdatePeriodPayment(mgmtfeeIdx);
		   
		   System.out.println(mgmtfeeRepository.selectMgmtfeeByMgmtfeeIdx(mgmtfeeIdx));
		   
	   }
	   
	   @Test
	   public void procedureMgmtOverDue() {
//		   List<Mgmtfee> list = mgmtfeeRepository.selectMgmtfeeListByAll();
//		   for (int i = 0; i < list.size(); i++) {
//			 System.out.println(list.get(i));
//		}
		   String mgmtfeeIdx = "100996";
		   mgmtfeeRepository.procedureMgmtOverDue(mgmtfeeIdx);
	   }

}
