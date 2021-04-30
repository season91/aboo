package com.kh.aboo.board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kh.aboo.user.generation.model.vo.Generation;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:aboo/src/main/webapp/WEB-INF/spring/**/*-context.xml"}) 
public class UsedBoardControllerTest {
	

	@Autowired
    private WebApplicationContext context; 
    private MockMvc mockMvc;
   

    @Before
    public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
    @Test
    public void usedPrivate_success() throws Exception {
    	Generation generation = new Generation();
    	generation.setId("grf000d000h");
    	generation.setPassword("park1003*");
    	generation.setBuilding("000");
    	generation.setNum("000");
    	generation.setGenerationIdx("100522");
    	generation.setApartmentIdx("100000");

    	this.mockMvc.perform(post("/board/used/usedprivate")
    			.sessionAttr("generation", generation)
    			.param("usedIdx","u100276")
    			).andDo(print());
    	
    }
    
    @Test
    public void usedDelete_success() throws Exception {
    	Generation generation = new Generation();
    	generation.setId("grf000d000h");
    	generation.setPassword("park1003*");
    	generation.setBuilding("000");
    	generation.setNum("000");
    	generation.setGenerationIdx("100522");
    	generation.setApartmentIdx("100000");

    	this.mockMvc.perform(post("/board/used/useddelete")
    			.sessionAttr("generation", generation)
    			.param("usedIdx","u100276")
    			).andDo(print());
    	
    }
    
    @Test
    public void usedCmtUpload_success() throws Exception {
    	Generation generation = new Generation();
    	generation.setId("grf000d000h");
    	generation.setPassword("park1003*");
    	generation.setBuilding("000");
    	generation.setNum("000");
    	generation.setGenerationIdx("100522");
    	generation.setApartmentIdx("100000");

    	this.mockMvc.perform(post("/board/used/usedcmtupload")
    			.sessionAttr("generation", generation)
    			.param("usedIdx","u100276")
    			.param("usedCmtContent","댓글테스트")
    			).andDo(print());
    	
    }
    
    @Test
    public void usedBrdCmtModify_success() throws Exception {
    	Generation generation = new Generation();
    	generation.setId("grf000d000h");
    	generation.setPassword("park1003*");
    	generation.setBuilding("000");
    	generation.setNum("000");
    	generation.setGenerationIdx("100522");
    	generation.setApartmentIdx("100000");

    	this.mockMvc.perform(post("/board/used/usedbrdcmtmodify")
    			.sessionAttr("generation", generation)
    			.param("usedCmtIdx","100260")
    			.param("usedCmtContent","댓글테스트2")
    			).andDo(print());
    	
    }
   
    @Test
    public void usedBrdCmtDelete_success() throws Exception {
    	Generation generation = new Generation();
    	generation.setId("grf000d000h");
    	generation.setPassword("park1003*");
    	generation.setBuilding("000");
    	generation.setNum("000");
    	generation.setGenerationIdx("100522");
    	generation.setApartmentIdx("100000");

    	this.mockMvc.perform(post("/board/used/usedbrdcmtdelete")
    			.sessionAttr("generation", generation)
    			.param("usedCmtIdx","100260")
    			).andDo(print());
    	
    }   
    
    @Test
    public void usedBrdCmtPrivate_success() throws Exception {
    	Generation generation = new Generation();
    	generation.setId("grf000d000h");
    	generation.setPassword("park1003*");
    	generation.setBuilding("000");
    	generation.setNum("000");
    	generation.setGenerationIdx("100522");
    	generation.setApartmentIdx("100000");

    	this.mockMvc.perform(post("/board/used/usedbrdcmtprivate")
    			.sessionAttr("generation", generation)
    			.param("usedCmtIdx","100260")
    			).andDo(print());
    	
    } 
    
}
