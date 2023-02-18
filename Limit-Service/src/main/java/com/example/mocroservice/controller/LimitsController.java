package com.example.mocroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mocroservice.beans.Limits;
import com.example.mocroservice.config.LimitServiceConfiguration;

@RestController
public class LimitsController {
	
	@Autowired
	private LimitServiceConfiguration configuration;
	
	@GetMapping("/limits")
	public Limits retrievElimits() {
		return new Limits(configuration.getMinimum(), configuration.getMaximum());
	}

}
