package com.example.mocroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={
		"com.example.mocroservice", "com.example.mocroservice.proxy"})
@EnableFeignClients
public class CurrrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrrencyConversionServiceApplication.class, args);
	}

}
