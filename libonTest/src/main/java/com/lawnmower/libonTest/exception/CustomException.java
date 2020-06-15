package com.lawnmower.libonTest.exception;

public class CustomException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public String libelle;

	public CustomException(String libelle) {
		super();
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
