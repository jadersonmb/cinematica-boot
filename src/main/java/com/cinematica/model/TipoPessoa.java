package com.cinematica.model;


public enum TipoPessoa {
	
	F("Física"), 
	J("Jurídica");
	
	private String value;

	private TipoPessoa(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
