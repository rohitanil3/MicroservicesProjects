package com.onesolvent.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClient(name="chat-a-bot" ,configuration = RibbonConfiguration.class)
public class UserAppApplication {
	
	 @Autowired
	    RestTemplate restTemplate;
	
	 @RequestMapping("/invoke")
	public String invoke() {
		
		return this.restTemplate.getForObject("http://chat-a-bot/app/chat", String.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}
	
	
}
