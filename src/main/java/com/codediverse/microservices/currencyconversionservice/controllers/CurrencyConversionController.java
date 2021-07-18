package com.codediverse.microservices.currencyconversionservice.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.codediverse.microservices.currencyconversionservice.proxies.CurrencyExchangeProxy;
import com.codediverse.microservices.currencyconversionservice.ro.CurrencyConversion;

@RestController
public class CurrencyConversionController {

	private static Logger logger = LogManager.getLogger(CurrencyConversionController.class);
	
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from , @PathVariable String to , @PathVariable String quantity)
	{
		 logger.info("calculateCurrencyConversion");
		
		return new CurrencyConversion(1000L, from, to, null, null, null, quantity);
	}
	
	
	
	  @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}") 
	  public CurrencyConversion calculateCurrencyConversionFeign(
			  @PathVariable String from ,
			  @PathVariable String to , 
			  @PathVariable String quantity) {
	  
		  logger.info("calculateCurrencyConversionFeign");
		  
	  CurrencyConversion currencyConversion= currencyExchangeProxy.retrieveExchangeValue(from, to);
	  
	  return currencyConversion; }
	 
}
