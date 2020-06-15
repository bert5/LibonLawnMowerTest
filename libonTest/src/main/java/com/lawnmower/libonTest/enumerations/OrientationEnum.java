package com.lawnmower.libonTest.enumerations;

public enum OrientationEnum {
	
	WEST("W"),
	NORTH("N"),
	EAST("E"),
	SOUTH("S");
	
	private String orientation;	
	
	private OrientationEnum(String orientation) {
		this.orientation = orientation;
	}
	
	public String getOrientation() {
		return this.orientation;
	}

}
