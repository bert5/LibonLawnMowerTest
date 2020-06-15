package com.lawnmower.libonTest.entity;

import java.util.List;

import org.javatuples.Pair;

public class LawnMower {
	
	private String name;
	private Pair<Integer,Integer> currentPosition;
	private String orientation;
	private List<Character> orders;
	
	public LawnMower(String name, Pair<Integer, Integer> currentPosition, String orientation, List<Character> orders) {
		super();
		this.name = name;
		this.currentPosition = currentPosition;
		this.orientation = orientation;
		this.orders = orders;
	}	
	
	public LawnMower() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pair<Integer, Integer> getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(Pair<Integer, Integer> currentPosition) {
		this.currentPosition = currentPosition;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public List<Character> getOrders() {
		return orders;
	}
	public void setOrders(List<Character> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentPosition == null) ? 0 : currentPosition.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LawnMower other = (LawnMower) obj;
		if (currentPosition == null) {
			if (other.currentPosition != null)
				return false;
		} else if (!currentPosition.equals(other.currentPosition))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (orientation == null) {
			if (other.orientation != null)
				return false;
		} else if (!orientation.equals(other.orientation))
			return false;
		return true;
	}
}
