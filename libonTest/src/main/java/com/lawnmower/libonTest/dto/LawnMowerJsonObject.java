package com.lawnmower.libonTest.dto;

public class LawnMowerJsonObject {
	
	private String payLoad;

	public LawnMowerJsonObject(String payLoad) {
		super();
		this.payLoad = payLoad;
	}

	public LawnMowerJsonObject() {
		super();
	}

	public String getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(String payLoad) {
		this.payLoad = payLoad;
	}	
}
