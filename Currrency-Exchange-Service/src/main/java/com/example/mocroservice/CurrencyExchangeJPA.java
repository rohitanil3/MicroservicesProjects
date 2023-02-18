package com.example.mocroservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mocroservice.bean.CurrencyExchange;

public interface CurrencyExchangeJPA extends JpaRepository<CurrencyExchange,Long> {
	
	CurrencyExchange findByFromAndTo(String from,String to);

}
