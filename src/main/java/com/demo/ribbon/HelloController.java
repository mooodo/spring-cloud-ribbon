/*
 * Created by 2020-05-03 22:04:22 
 */
package com.demo.ribbon;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author fangang
 */
@RestController
@RequestMapping("ribbon")
public class HelloController {
	@Autowired
	private RestTemplate restTemplate;
	@GetMapping("sayHello")
	@HystrixCommand(fallbackMethod="error")
	public String sayHello(String user) {
		String url = "http://Service-Hello/sayHello?user= {user}";
		return restTemplate.getForObject(url, String.class, user);
	}
	@GetMapping("showMe")
	public Person showMe() {
		String url = "http://Service-Hello/showMe";
		return restTemplate.getForObject(url, Person.class);
	}
	
	@PostMapping("findPerson")
	public Person findPerson(@RequestBody Map<String, String> param) {
		String url = "http://Service-Hello/findPerson";
		return restTemplate.postForObject(url, param, Person.class);
	}
	
	public String error(String user) {
		return "Sorry, something wrong!";
	}
}
