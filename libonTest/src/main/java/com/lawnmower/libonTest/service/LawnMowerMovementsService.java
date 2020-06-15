package com.lawnmower.libonTest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lawnmower.libonTest.dto.LawnMowerJsonObject;
import com.lawnmower.libonTest.entity.LawnMower;
import com.lawnmower.libonTest.utils.AppUtils;
import com.lawnmower.libonTest.utils.LawnMowerMovingUtils;

@Service
public class LawnMowerMovementsService {
	
	private Logger log = LogManager.getLogger(LawnMowerMovementsService.class);
	
	public void executeOrdersFromPayload(LawnMowerJsonObject lawnMowerJsonObject) {
		
		HashMap<String, ArrayList<Character>> hashMapPayLoad = LawnMowerMovingUtils.extractInformationsFromPayload(lawnMowerJsonObject.getPayLoad());
		
		log.info("The grassgrid size is : "  +  hashMapPayLoad.get("charsGrassGridSize").toString());
		 
		LinkedList<LawnMower> lawnMowers = LawnMowerMovingUtils.extractLawnMowerFromHashMapInfoPayload(hashMapPayLoad);
		
		log.info("The first lawnmower has the following orders : " + lawnMowers.get(0).getOrders().toString());
	}

}
