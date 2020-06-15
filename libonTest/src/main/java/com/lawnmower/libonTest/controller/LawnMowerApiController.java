package com.lawnmower.libonTest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawnmower.libonTest.dto.LawnMowerJsonObject;
import com.lawnmower.libonTest.exception.CustomException;
import com.lawnmower.libonTest.service.LawnMowerMovementsService;
import com.lawnmower.libonTest.utils.AppUtils;

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
		String appel = AppUtils.getBody(request);
		LawnMowerJsonObject lawnMowerJsonObjectSend = AppUtils.deserialiseJsonToEntity(LawnMowerJsonObject.class, appel);
		try {
			LawnMowerJsonObject lawnMowerJsonObjectReturned = lawnMowerMovementsService.executeOrdersFromPayload(lawnMowerJsonObjectSend);
			AppUtils.sendResponse(response, lawnMowerJsonObjectReturned);
			return ResponseEntity.ok().build();
		} catch (CustomException ce) {
			log.error(ce.getMessage());
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ce.getLibelle());
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
	}

}
