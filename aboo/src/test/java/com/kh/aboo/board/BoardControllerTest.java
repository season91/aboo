package com.kh.aboo.board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"}) 
public class BoardControllerTest {
	

	@Autowired
    private WebApplicationContext context; 
    private MockMvc mockMvc;
   

    @Before
    public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
    @Test
    public void uploadBoard() throws Exception {
    	MockMultipartFile file1 = new MockMultipartFile("files", "OFM.txt", null, "firstFile".getBytes());
    	MockMultipartFile file2 = new MockMultipartFile("files", "OFM2.txt", null, "secondFile".getBytes());
    	
    	this.mockMvc.perform(multipart("/board/upload")
    			.file(file1)
    			.file(file2)
    			.contentType(MediaType.MULTIPART_FORM_DATA)
    			.param("title","게시글테스트")
    			.param("content","목객체를 사용한 게시글테스트 입니다.")
    			).andDo(print());
    	
    }
    
    @Test
    public void boardList() throws Exception {
    	this.mockMvc.perform(get("/board/list")
    			.queryParam("page", "1")
    			).andDo(print()).andExpect(status().isOk());
    }
	   
}
