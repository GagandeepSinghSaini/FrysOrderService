package com.frys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrysOrderServiceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(FrysOrderServiceApplication.class);
	
	public static void main(String[] args) {
		int value = 10;
		LOGGER.trace("doStuff needed more information - {}", value);
	    LOGGER.debug("doStuff needed to debug - {}", value);
	    LOGGER.info("doStuff took input - {}", value);
	    LOGGER.warn("doStuff needed to warn - {}", value);
	LOGGER.error("doStuff encountered an error with value - {}", value);
		SpringApplication.run(FrysOrderServiceApplication.class, args);
	}
	
}
