package com.lawnmower.libonTest.utils;

import org.javatuples.Pair;

import com.lawnmower.libonTest.enumerations.OrientationEnum;

public class NextPositionUtils {
	
	public static String getNextRightOrientation(String orientation) throws Exception {
		String nextRightOrientation = null;
		switch (orientation) {
			case "N":
				nextRightOrientation = OrientationEnum.EAST.getOrientation();
			break;
			case "E":
				nextRightOrientation = OrientationEnum.SOUTH.getOrientation();
				break;
			case "S":
				nextRightOrientation = OrientationEnum.WEST.getOrientation();
				break;
			case "W":
				nextRightOrientation = OrientationEnum.NORTH.getOrientation();
				break;
		default:
			throw new Exception("Bad call to fonction getNextRightOrientation : " + orientation);
		}
		return nextRightOrientation;		
	}
	
	public static String getNextLeftOrientation(String orientation) throws Exception {
		String nextLeftOrientation = null;
		switch (orientation) {
			case "N":
				nextLeftOrientation = OrientationEnum.WEST.getOrientation();
			break;
			case "E":
				nextLeftOrientation = OrientationEnum.NORTH.getOrientation();
				break;
			case "S":
				nextLeftOrientation = OrientationEnum.EAST.getOrientation();
				break;
			case "W":
				nextLeftOrientation = OrientationEnum.SOUTH.getOrientation();
				break;
		default:
			throw new Exception("Bad call to fonction getNextLeftOrientation : " + orientation);
		}
		return nextLeftOrientation;		
	}
	
	public static Pair<Integer,Integer> getNextCoordinates(Pair<Integer,Integer> grassGridSize, String orientation, Pair<Integer,Integer> currentPosition) throws Exception{
		Pair<Integer,Integer> nextPosition = null;
		Integer yPosition;
		Integer xPosition;
		switch (orientation) {
		case "N":
			yPosition = currentPosition.getValue1() + 1 > grassGridSize.getValue1() ? currentPosition.getValue1() : currentPosition.getValue1()+1;
			nextPosition = new Pair<Integer, Integer>(currentPosition.getValue0(),yPosition);
		break;
		case "E":
			xPosition = currentPosition.getValue0() + 1 > grassGridSize.getValue0() ? currentPosition.getValue0() : currentPosition.getValue0()+1;
			nextPosition = new Pair<Integer, Integer>(xPosition,currentPosition.getValue1());
			break;
		case "S":
			yPosition = currentPosition.getValue1() == 0 ? currentPosition.getValue1() : currentPosition.getValue1() - 1;
			nextPosition = new Pair<Integer, Integer>(currentPosition.getValue0(),yPosition);
			break;
		case "W":
			xPosition = currentPosition.getValue0() == 0 ? currentPosition.getValue0() : currentPosition.getValue0()-1;
			nextPosition = new Pair<Integer, Integer>(xPosition,currentPosition.getValue1());
			break;
	default:
		throw new Exception("Bad call to fonction getNextRightOrientation : " + orientation);
	}
		return nextPosition;
	}

}
