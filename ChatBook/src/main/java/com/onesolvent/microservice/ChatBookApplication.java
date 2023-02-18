package com.onesolvent.microservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/app")
public class ChatBookApplication {
	
	@Value("${server.port}")
	private String port;

	public static void main(String[] args) {
		SpringApplication.run(ChatBookApplication.class, args);
	}

	@GetMapping("/chat")
	public String chat() {
		return "whatsapp on "+port;
	}
}
