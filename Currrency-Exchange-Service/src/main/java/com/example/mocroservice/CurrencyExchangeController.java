package com.example.mocroservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.mocroservice.bean.CurrencyExchange;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeJPA currencyExchangeJPA;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		//CurrencyExchange c=new CurrencyExchange(100L, from, to, BigDecimal.valueOf(50.0));
		System.out.println("inside controller");
		CurrencyExchange c=currencyExchangeJPA.findByFromAndTo(from,to);
		if(c==null) {
			throw new NullPointerException("No Obj present");
		}
		c.setEnv(environment.getProperty("local.server.port"));
		return c;
		
	}

}
