package com.onesolvent.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class RateLimitController {
	
	 Logger logger = LoggerFactory.getLogger(RateLimitController.class);
     RestTemplate restTemplate= new RestTemplate();
	
	@GetMapping("/get")
	 @RateLimiter(name = "ratelimiter" , fallbackMethod = "getFallbackResponse")
	 public String getMessage() {
		  return "success";
	 }
	
	

     @GetMapping("/getInvoice")
     @Retry(name = "getInvoiceRetry", fallbackMethod = "getInvoiceFallback") 
     public String getInvoice() {
        logger.info("getInvoice() call starts here");
        ResponseEntity<String> entity= restTemplate.getForEntity("http://localhost:8080/invoice/rest/find/2", String.class);
        logger.info("Response :" + entity.getStatusCode());
        return entity.getBody();
     }

     public String getInvoiceFallback(Exception e) {
        logger.info("---RESPONSE FROM FALLBACK METHOD---");
        return "SERVICE IS DOWN, PLEASE TRY AFTER SOMETIME !!!";
     }

}
