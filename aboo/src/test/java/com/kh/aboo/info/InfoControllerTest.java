package com.kh.aboo.info;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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

import com.kh.aboo.board.info.model.vo.InfoBoard;
import com.kh.aboo.board.info.model.vo.InfoCmt;
import com.kh.aboo.user.generation.model.vo.Generation;

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
	public void uploadBoard() throws Exception{
		
		Generation generation = new Generation();
		generation.setApartmentIdx("100020");
		generation.setBuilding("101");
		generation.setNum("103");
		generation.setGenerationIdx("100540");
		
		InfoBoard infoBoard = new InfoBoard();
		
		infoBoard.setbCategory("정보");
		infoBoard.setbTitle("원데이클래스 관심있으신 분들 정보공유합니다.");
		infoBoard.setbContent("<p>제가 원데이클래스 관심이 많아서 여기저기 많이 다녀봤는 데 정보공유하고자 후기 남겨요!</p>");
		
		mockMvc.perform(post("/board/info/upload")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.sessionAttr("generation", generation)
				.param("bCategory", infoBoard.getbCategory())
				.param("bTitle", infoBoard.getbTitle())
				.param("bContent", infoBoard.getbContent())
				).andDo(print());
	}
	
	@Test
	public void uploadInfoCmt() throws Exception{
		
		Generation generation = new Generation();
		generation.setBuilding("101");
		generation.setNum("102");

		
		InfoCmt infoCmt = new InfoCmt();
		
		infoCmt.setbIdx("100211");
		infoCmt.setGenerationIdx("100581");
		infoCmt.setcContent("저도 참가하고 싶습니다! 2살 포메 키우고있어요~^^");
		
		mockMvc.perform(post("/board/info/uploadinfocmt")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.sessionAttr("generation", generation)
				.param("bIdx", infoCmt.getbIdx())
				.param("generationIdx",infoCmt.getGenerationIdx())
				.param("cContent", infoCmt.getcContent())
				).andDo(print());
	}
	

}
