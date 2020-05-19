/*
 * Created by 2020-05-04 16:02:17 
 */
package com.demo.ribbon;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author fangang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(ids= {"com.demo:spring-cloud-service:+:stubs:8762"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class HelloControllerTest {
	@Autowired
	MockMvc mvc;
	@Test
	public void testSayHello() throws Exception {
		mvc.perform(get("/ribbon/sayHello").param("user", "Johnwood"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hi Johnwood, welcome to you! The server port is null"));
	}
	
	@Test
	public void testShowMe() throws Exception {
		mvc.perform(get("/ribbon/showMe"))
			.andExpect(status().isOk())
			.andExpect(content().json("{'id':0,'name':'Mooodo','gender':'male'}"));
	}
	
	@Test
	public void testFindPerson() throws Exception {
		mvc.perform(get("/ribbon/findPerson?name={name}&gender={gender}", "Mooodo","male"))
			.andExpect(status().isOk())
			.andExpect(content().json("{'id':0,'name':'Mooodo','gender':'male'}"));
	}
}
