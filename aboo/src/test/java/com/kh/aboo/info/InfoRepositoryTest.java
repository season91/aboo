package com.kh.aboo.info;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.aboo.board.info.model.repository.InfoRepository;
import com.kh.aboo.board.info.model.vo.InfoBoard;

@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class InfoRepositoryTest {
	
	@Autowired
	InfoRepository infoRepository;


	@Test
	public void insertInfoBoard(){
		
		InfoBoard infoBoard = new InfoBoard();
		infoBoard.setApartmentIdx("100020");
		infoBoard.setGenerationIdx("100540");
		infoBoard.setbWriter("101동 103호");
		infoBoard.setbCategory("정보");
		infoBoard.setbTitle("1동 103호 포장이사업체 이용후기입니다.");
		infoBoard.setbContent("<p>안녕하세요! 이번에 101동 103호에 새로 입주한 회원입니다! 제가 이번에 이용한 포장이사업체가 너무 친절했어서 정보공유해요~.</p><p>포장 하나하나 신경써주시고 사라진 물건은 없나 다 체크해주셨습니다. 가격도 저렴하게 받아주셨어요!><</p><p>다음번 이사하실 때 꼭 이용하시길 바라요.</p>");

		infoRepository.insertInfoBoard(infoBoard);
	}

	

}
