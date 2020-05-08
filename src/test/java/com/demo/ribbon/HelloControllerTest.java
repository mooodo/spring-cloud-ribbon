/*
 * Created by 2020-05-04 16:02:17 
 */
package com.demo.ribbon;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


/**
 * @author fangang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=RibbonApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(ids= {"com.demo:spring-cloud-service:+:stubs:8762"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class HelloControllerTest {
	@Autowired
	RestTemplate restTemplate;
	@Test
	public void testSayHello() {
		String url = "http://localhost:8762/sayHello?user= {user}";
		String user = "Johnwood";
		String actual = restTemplate.getForObject(url, String.class, user);
		assertThat(actual, equalTo("Hi Johnwood. The server port is 8762"));
	}
}
