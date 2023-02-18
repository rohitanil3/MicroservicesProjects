package com.example.mocroservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.mocroservice.bean.CurrencyConversion;
import com.example.mocroservice.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getConversion(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity) {
		
		Map<String,String>map=new HashMap<>();
		map.put("from", from);
		map.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class, 
				map);
		
		CurrencyConversion currencyConversion = responseEntity.getBody();
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		return currencyConversion;
		
		
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getConversionbyFeign(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity) {
		
		CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		currencyConversion.setEnv(currencyConversion.getEnv()+"from feign");
		return currencyConversion;
		
		
	}


}
