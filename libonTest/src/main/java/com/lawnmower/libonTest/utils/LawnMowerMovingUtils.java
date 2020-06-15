package com.lawnmower.libonTest.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.javatuples.Pair;

import com.lawnmower.libonTest.entity.LawnMower;
import com.lawnmower.libonTest.exception.CustomException;

public class LawnMowerMovingUtils {
	
	public static Logger log = LogManager.getLogger(LawnMowerMovingUtils.class);
	
	public static ArrayList<Character> getCharArrayListFromString(String string){
		return new ArrayList<Character>(string.chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
	}
	
	public static HashMap<String, ArrayList<Character>> extractInformationsFromPayload(String payload) throws CustomException{
		
		HashMap<String, ArrayList<Character>> hashMapResult = new HashMap<String, ArrayList<Character>>();
		
		String[] payLoadSplited = payload.split(Strings.LINE_SEPARATOR);			
		String grassGridSize = payLoadSplited[0].replaceAll("\\p{Blank}", "").toUpperCase();
		String initialPositionLawnMower1 = payLoadSplited[1].replaceAll("\\p{Blank}", "").toUpperCase();
		String ordersLawnMower1 = payLoadSplited[2].replaceAll("\\p{Blank}", "").toUpperCase();
		String initialPositionLawnMower2 = payLoadSplited[3].replaceAll("\\p{Blank}", "").toUpperCase();
		String ordersLawnMower2 = payLoadSplited[4].replaceAll("\\p{Blank}", "").toUpperCase();
		
		List<Character> charsGrassGridSize = getCharArrayListFromString(grassGridSize);
		checkGridSize(charsGrassGridSize);
		
		List<Character> charsInitialPositionLawnMower1 = getCharArrayListFromString(initialPositionLawnMower1);
		checkInitialPositionLawnMower(charsInitialPositionLawnMower1, "first lawnmower");
		
		List<Character> charsOrdersLawnMower1 = getCharArrayListFromString(ordersLawnMower1);
		checkOrdersLawnMower(charsOrdersLawnMower1, "first lawnmower");
		
		List<Character> charsInitialPositionLawnMower2 = getCharArrayListFromString(initialPositionLawnMower2);
		checkInitialPositionLawnMower(charsInitialPositionLawnMower2, "second lawnmower");
		
		List<Character> charsOrdersLawnMower2 = getCharArrayListFromString(ordersLawnMower2);
		checkOrdersLawnMower(charsOrdersLawnMower2, "second lawnmower");
		
		hashMapResult.put("charsGrassGridSize", getCharArrayListFromString(grassGridSize));
		hashMapResult.put("charsInitialPositionLawnMower1", getCharArrayListFromString(initialPositionLawnMower1));
		hashMapResult.put("charsOrdersLawnMower1", getCharArrayListFromString(ordersLawnMower1));
		hashMapResult.put("charsInitialPositionLawnMower2", getCharArrayListFromString(initialPositionLawnMower2));
		hashMapResult.put("charsOrdersLawnMower2", getCharArrayListFromString(ordersLawnMower2));
		
		return hashMapResult;
	}
	
	public static void checkGridSize(List<Character> charsGrassGridSize) throws CustomException {
		for(Character character : charsGrassGridSize) {
			if(!character.toString().matches(".*\\d.*")) {
				throw new CustomException("Le champ correspondant à la taille de la pelouse n'est pas conforme " + charsGrassGridSize.toString());
			}
		}
	}
	
	public static void checkInitialPositionLawnMower(List<Character> charsInitialPositionLawnMower, String lawnMowerName) throws CustomException {
		
		if(null == charsInitialPositionLawnMower || charsInitialPositionLawnMower.size() == 0 
				|| charsInitialPositionLawnMower.size() > 3) {
			throw new CustomException("Le champ correspondant à la position du " + lawnMowerName + " est mal formé " + charsInitialPositionLawnMower.toString());
		}
		for(Character character : charsInitialPositionLawnMower) {
			if (!character.toString().equalsIgnoreCase("N") && !character.toString().equalsIgnoreCase("S") && 
					!character.toString().equalsIgnoreCase("O") && !character.toString().equalsIgnoreCase("E") 
					&& !character.toString().matches(".*\\d.*")) {
				throw new CustomException("Le champ correspondant à la position du " + lawnMowerName + " contient un caractère non autorisé " + character.toString());
			}
		}
	}
	
	public static void checkOrdersLawnMower(List<Character> charsOrdersLawnMower, String lawnMowerName) throws CustomException {
		for(Character character : charsOrdersLawnMower) {
			if (!character.toString().equalsIgnoreCase("G") && !character.toString().equalsIgnoreCase("D") && 
					!character.toString().equalsIgnoreCase("A") && !character.toString().matches(".*\\d.*")) {
				throw new CustomException("Le champ correspondant aux ordres du " + lawnMowerName + " contient un caractère non autorisé " + character.toString());
			}
		}
	}
	
	public static LinkedList<LawnMower> extractLawnMowerFromHashMapInfoPayload(HashMap<String, ArrayList<Character>> hashMapPayload){
		LinkedList<LawnMower> lawnMowers = new LinkedList<LawnMower>();
		
		Pair<Integer,Integer> positionLawnMower1 = new Pair<Integer,Integer>(Integer.parseInt(hashMapPayload.get("charsInitialPositionLawnMower1").get(0).toString()), Integer.parseInt(hashMapPayload.get("charsInitialPositionLawnMower1").get(1).toString()));
		LawnMower lawnMower1 = new LawnMower("First LawnMower", positionLawnMower1, hashMapPayload.get("charsInitialPositionLawnMower1").get(2).toString(), hashMapPayload.get("charsOrdersLawnMower1"));

		Pair<Integer,Integer> positionLawnMower2 = new Pair<Integer,Integer>(Integer.parseInt(hashMapPayload.get("charsInitialPositionLawnMower2").get(0).toString()), Integer.parseInt(hashMapPayload.get("charsInitialPositionLawnMower2").get(1).toString()));
		LawnMower lawnMower2 = new LawnMower("Second LawnMower", positionLawnMower2, hashMapPayload.get("charsInitialPositionLawnMower2").get(2).toString(), hashMapPayload.get("charsOrdersLawnMower2"));
		
		lawnMowers.add(lawnMower1);
		lawnMowers.add(lawnMower2);	
		
		return lawnMowers;
	}
}
