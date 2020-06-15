package com.lawnmower.libonTest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import com.lawnmower.libonTest.dto.LawnMowerJsonObject;
import com.lawnmower.libonTest.entity.LawnMower;
import com.lawnmower.libonTest.exception.CustomException;
import com.lawnmower.libonTest.utils.LawnMowerMovingUtils;
import com.lawnmower.libonTest.utils.NextPositionUtils;

@Service
public class LawnMowerMovementsService {
	
	private Logger log = LogManager.getLogger(LawnMowerMovementsService.class);
	
	public LawnMowerJsonObject executeOrdersFromPayload(LawnMowerJsonObject lawnMowerJsonObject) throws CustomException, Exception {
		
		HashMap<String, ArrayList<Character>> hashMapPayLoad = LawnMowerMovingUtils.extractInformationsFromPayload(lawnMowerJsonObject.getPayLoad());
		
		log.info("The grassgrid size is : "  +  hashMapPayLoad.get("charsGrassGridSize").toString());
		 
		LinkedList<LawnMower> lawnMowers = LawnMowerMovingUtils.extractLawnMowerFromHashMapInfoPayload(hashMapPayLoad);
		
		Pair<Integer, Integer> grassGridSize = new Pair<Integer, Integer>(Integer.parseInt(hashMapPayLoad.get("charsGrassGridSize").get(0).toString()), Integer.parseInt(hashMapPayLoad.get("charsGrassGridSize").get(1).toString()));		
		
		return new LawnMowerJsonObject(executeAllOrders(lawnMowers, grassGridSize));		
	}
	
	public String executeAllOrders(LinkedList<LawnMower> lawnMowers, Pair<Integer,Integer> size) throws Exception{
		
		StringBuilder payLoadResult = new StringBuilder();
		
		log.info("Moving " + lawnMowers.get(0).getName() + " , looking to : " + lawnMowers.get(0).getOrientation() +  " from : " + lawnMowers.get(0).getCurrentPosition().toString() + " with orders : " + lawnMowers.get(0).getOrders().toString());
		LawnMower lawnMower1 = executeLawnMowerOrders(size, lawnMowers.get(0));
		payLoadResult.append(lawnMower1.getCurrentPosition().getValue0() + " " + lawnMower1.getCurrentPosition().getValue1() + " " + lawnMower1.getOrientation() + Strings.LINE_SEPARATOR);
		
		log.info("Moving " + lawnMowers.get(1).getName() + " , looking to : " + lawnMowers.get(1).getOrientation() +  " from : " + lawnMowers.get(1).getCurrentPosition().toString() + " with orders : " + lawnMowers.get(1).getOrders().toString());
		LawnMower lawnMower2 = executeLawnMowerOrders(size, lawnMowers.get(1));
		payLoadResult.append(lawnMower2.getCurrentPosition().getValue0() + " " + lawnMower2.getCurrentPosition().getValue1() + " " + lawnMower2.getOrientation());
		
		return payLoadResult.toString();
	}
	
	public LawnMower executeLawnMowerOrders(Pair<Integer,Integer> grassGridSize, LawnMower lawnMower) throws Exception{
		for(Character character : lawnMower.getOrders()) {
			log.info(lawnMower.getName() + " will make the following move : " + character);
			switch (character.toString()) {
			case "G": {
				String nextLeftOrientation = NextPositionUtils.getNextLeftOrientation(lawnMower.getOrientation());
				lawnMower.setOrientation(nextLeftOrientation);
				log.info(lawnMower.getName() + " oriented to : " + lawnMower.getOrientation() + lawnMower.getCurrentPosition().toString());
				break;
			}
			case "A": {
				Pair<Integer,Integer> nextCoordinates = NextPositionUtils.getNextCoordinates(grassGridSize, lawnMower.getOrientation(), lawnMower.getCurrentPosition());
				lawnMower.setCurrentPosition(nextCoordinates);
				log.info(lawnMower.getName() + " has new coordinates : " + lawnMower.getCurrentPosition().toString());
				break;
			}
			case "D": {
				String nextRightOrientation = NextPositionUtils.getNextRightOrientation(lawnMower.getOrientation());
				lawnMower.setOrientation(nextRightOrientation);
				log.info(lawnMower.getName() + " oriented to : " + lawnMower.getOrientation());
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value");
				
			}			
		}
		return lawnMower;
	}

}
