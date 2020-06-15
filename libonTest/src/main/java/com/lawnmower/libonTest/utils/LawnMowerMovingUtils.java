package com.lawnmower.libonTest.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.javatuples.Pair;

import com.lawnmower.libonTest.entity.LawnMower;

public class LawnMowerMovingUtils {
	
	public static ArrayList<Character> getCharArrayListFromString(String string){
		return new ArrayList<Character>(string.chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
	}
	
	public static HashMap<String, ArrayList<Character>> extractInformationsFromPayload(String payload){
		
		HashMap<String, ArrayList<Character>> hashMapResult = new HashMap<String, ArrayList<Character>>();
		
		String[] payLoadSplited = payload.split(Strings.LINE_SEPARATOR);			
		String grassGridSize = payLoadSplited[0].replaceAll("\\p{Blank}", "");
		String initialPositionLawnMower1 = payLoadSplited[1].replaceAll("\\p{Blank}", "");
		String ordersLawnMower1 = payLoadSplited[2].replaceAll("\\p{Blank}", "");
		String initialPositionLawnMower2 = payLoadSplited[3].replaceAll("\\p{Blank}", "");
		String ordersLawnMower2 = payLoadSplited[4].replaceAll("\\p{Blank}", "");
		
		hashMapResult.put("charsGrassGridSize", getCharArrayListFromString(grassGridSize));
		hashMapResult.put("charsInitialPositionLawnMower1", getCharArrayListFromString(initialPositionLawnMower1));
		hashMapResult.put("charsOrdersLawnMower1", getCharArrayListFromString(ordersLawnMower1));
		hashMapResult.put("charsInitialPositionLawnMower2", getCharArrayListFromString(initialPositionLawnMower2));
		hashMapResult.put("charsOrdersLawnMower2", getCharArrayListFromString(ordersLawnMower2));
		
		return hashMapResult;
	}
	
	public static LinkedList<LawnMower> extractLawnMowerFromHashMapInfoPayload(HashMap<String, ArrayList<Character>> hashMapPayload){
		LinkedList<LawnMower> lawnMowers = new LinkedList<LawnMower>();
		
		Pair<Integer,Integer> positionLawnMower1 = new Pair<Integer,Integer>(Integer.parseInt(hashMapPayload.get("charsInitialPositionLawnMower1").get(0).toString()), Integer.parseInt(hashMapPayload.get("charsInitialPositionLawnMower1").get(1).toString()));
		LawnMower lawnMower1 = new LawnMower(positionLawnMower1, hashMapPayload.get("charsInitialPositionLawnMower1").get(2).toString(), hashMapPayload.get("charsOrdersLawnMower1"));

		Pair<Integer,Integer> positionLawnMower2 = new Pair<Integer,Integer>(Integer.parseInt(hashMapPayload.get("charsInitialPositionLawnMower2").get(0).toString()), Integer.parseInt(hashMapPayload.get("charsInitialPositionLawnMower2").get(1).toString()));
		LawnMower lawnMower2 = new LawnMower(positionLawnMower2, hashMapPayload.get("charsInitialPositionLawnMower2").get(2).toString(), hashMapPayload.get("charsOrdersLawnMower2"));
		
		lawnMowers.add(lawnMower1);
		lawnMowers.add(lawnMower2);	
		
		return lawnMowers;
	}
}
