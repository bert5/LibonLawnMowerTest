package com.lawnmower.libonTest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawnmower.libonTest.service.LawnMowerMovementsService;

@RestController
public class LawnMowerApiController {
	
	@Autowired
	private LawnMowerMovementsService lawnMowerMovementsService;
	
	private Logger log = LogManager.getLogger(LawnMowerApiController.class);
	
	public LawnMowerApiController(LawnMowerMovementsService lawnMowerMovementsService) {
		super();
		this.lawnMowerMovementsService = lawnMowerMovementsService;
	}
	
	public LawnMowerApiController() {
		super();
	}
	
	@PostMapping("/MovementsInformationsV1")
	public ResponseEntity<?> getMovingInformations(HttpServletRequest request, HttpServletResponse response){
		log.info("Can call LawnMowerApiController");
		lawnMowerMovementsService.executeOrdersFromPayload();
		return null;
	}

}
