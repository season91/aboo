package com.kh.aboo.car;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration // 프로젝트의 web.xml 대신 테스트를 위해 생성된 가상 web.xml 사용한다.
@RunWith(SpringJUnit4ClassRunner.class) // Junit 단위테스트 프레임워크의 실행방법을 지정해준다. 테스트때 사용할 가상의 applicationContext를 생성하고 관리해준다.
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"}) //가상 applicationContext 가 읽을 스프링 빈 설정파일의 위치를 지정.
public class CarControllerTest {
   
   @Autowired
   private WebApplicationContext context; 
   
   private MockMvc mockMvc;
   
   @Before
   public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
   }
   
  
   
}