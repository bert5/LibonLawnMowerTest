package com.lawnmower.libonTest.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LawnMowerMovementsService {
	
	private Logger log = LogManager.getLogger(LawnMowerMovementsService.class);
	
	public void executeOrdersFromPayload() {
		log.info("Can call LawnMowerMovementsService");
	}

}
